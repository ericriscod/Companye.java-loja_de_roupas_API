package com.onlinestore.modabit.services;

import java.time.LocalDate;
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
import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.entities.Sale;
import com.onlinestore.modabit.entities.payments.DebitCard;
import com.onlinestore.modabit.repositories.CartShoppingRepository;
import com.onlinestore.modabit.repositories.PaymentRepository;
import com.onlinestore.modabit.repositories.ProductRepository;
import com.onlinestore.modabit.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private CartShoppingRepository cartRepository;

	@Autowired
	private CartShoppingService cartShoppingService;

	@Autowired
	private ProductService productService;

	// Armazenar quantidades por sku para validação da venda.
	private Map<String, Integer> map = new HashMap<>();

	public Page<Sale> findAll(Pageable pageable) {
		return saleRepository.findAll(pageable);
	}

	public List<Sale> findAll() {
		return saleRepository.findAll();
	}

	public Optional<Sale> findById(Long id) {

		if (saleRepository.findById(id).isPresent()) {
			return saleRepository.findById(id);
		}

		throw new NoSuchElementException("Product not found");
	}

	public Sale validateSale(DebitCard debitCard, String cpf, LocalDate moment) {

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
		Sale sale = new Sale(debitCard, cpf, moment, cartShopping);

		paymentRepository.save(debitCard);
		cartRepository.save(cartShopping);
		saleRepository.save(sale);

		return sale;
	}

	public Sale validateSale(DebitCard debitCard, LocalDate moment) {

		if (cartShoppingService.findAll().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		// Validação da quantidade no estoque se há produtos disponíveis
		validateStock();

		CartShopping cartShopping = new CartShopping();
		cartShopping.setProducts(cartShoppingService.findAll());

		Sale sale = new Sale(debitCard, moment, cartShopping);

		for (String sku : map.keySet()) {
			Product prod = productService.findBySku(sku);
			
			prod.getStock().setQuantity(map.get(sku));

			productRepository.save(prod);
		}

		paymentRepository.save(debitCard);
		cartRepository.save(cartShopping);
		saleRepository.save(sale);

		return sale;
	}

	private void validateStock() {

		map.clear();

		for (Product cartShopping : cartShoppingService.findAll()) {
			Integer quantityInStock = productService.findBySku(cartShopping.getSku()).getStock().getQuantity();

			if (quantityInStock < cartShopping.getStock().getQuantity()) {
				throw new IllegalArgumentException("The quantity in the cart shopping exceeds stock");
			}

			// Quantidades para serem atualizadas

			Integer stock = productService.findBySku(cartShopping.getSku()).getStock().getQuantity();
			Integer cart = cartShopping.getStock().getQuantity();

			map.put(cartShopping.getSku(), (stock - cart));
		}
	}
}