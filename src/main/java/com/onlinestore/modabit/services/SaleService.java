package com.onlinestore.modabit.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinestore.modabit.entities.CartShopping;
import com.onlinestore.modabit.entities.PaymentMethod;
import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.entities.Sale;
import com.onlinestore.modabit.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Autowired
	private CartShoppingService cartShoppingService;

	@Autowired
	private ProductService productService;

	// Armazenar quantidades por sku para validação da venda.
	private Map<String, Integer> map;

	public Page<Sale> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<Sale> findAll() {
		return repository.findAll();
	}

	public Optional<Sale> findById(Long id) {

		if (repository.findById(id).isPresent()) {
			return repository.findById(id);
		}

		throw new NoSuchElementException("Product not found");
	}

	public Sale validateSale(PaymentMethod paymentMethod, String cpf, LocalDateTime moment) {

		if (cartShoppingService.findAll().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		if (cpf.length() != 11 || cpf.contains(".") || cpf.contains("-")) {

			throw new IllegalArgumentException("Invalid CPF");
		}

		// Validação da quantidade no estoque se há produtos disponíveis
		validateStock();

		for (String sku : map.keySet()) {
			productService.findBySku(sku).getStock().setQuantity(map.get(sku));
		}

		CartShopping cartShopping = new CartShopping(cartShoppingService.findAll());
		Sale sale = new Sale(paymentMethod, cpf, moment, cartShopping);

		repository.save(sale);

		return sale;
	}

	public Sale validateSale(PaymentMethod paymentMethod, LocalDateTime moment) {

		if (cartShoppingService.findAll().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		// Validação da quantidade no estoque se há produtos disponíveis
		validateStock();

		for (String sku : map.keySet()) {
			productService.findBySku(sku).getStock().setQuantity(map.get(sku));
		}

		CartShopping cartShopping = new CartShopping(cartShoppingService.findAll());
		Sale sale = new Sale(paymentMethod, moment, cartShopping);

		repository.save(sale);

		return sale;
	}

	private void validateStock() {

		for (Product cartShopping : cartShoppingService.findAll()) {
			Integer quantityInStock = productService.findBySku(cartShopping.getSku()).getStock().getQuantity();

			if (quantityInStock < cartShopping.getStock().getQuantity()) {
				throw new IllegalArgumentException("The quantity in the cart shopping exceeds stock");
			}

			// Quantidades para serem atualizadas

			Integer stock = productService.findBySku(cartShopping.getSku()).getStock().getQuantity();
			Integer cart = cartShopping.getStock().getQuantity();

			map = new HashMap<>();
			map.put(cartShopping.getSku(), (stock - cart));
		}
	}
}