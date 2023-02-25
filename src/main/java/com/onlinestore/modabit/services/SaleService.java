package com.onlinestore.modabit.services;

import java.time.LocalDate;
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
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public Sale validateSale(DebitCard debitCard) {

		if (cartShoppingService.findAll().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		CartShopping cartShopping = new CartShopping(cartShoppingService.findAll());


		// Validação da quantidade no estoque
		validateStock();

		Sale sale = new Sale(debitCard, LocalDateTime.now(), cartShopping);

		paymentRepository.save(debitCard);
		cartRepository.save(cartShopping);
		saleRepository.save(sale);

		// Atualizando estoque
		for (String sku : map.keySet()) {
			Product prod = productService.findBySku(sku);

			prod.getStock().setQuantity(map.get(sku));
			productRepository.save(prod);
		}
		
		
		return sale;
	}
	
	@Transactional
	public Sale validateSale(String cpf, DebitCard debitCard) {

		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Invalid cpf");
		}
		
		if (cartShoppingService.findAll().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		CartShopping cartShopping = new CartShopping(cartShoppingService.findAll());


		// Validação da quantidade no estoque
		validateStock();

		Sale sale = new Sale(debitCard, cpf, LocalDateTime.now(), cartShopping);

		paymentRepository.save(debitCard);
		cartRepository.save(cartShopping);
		saleRepository.save(sale);

		// Atualizando estoque
		for (String sku : map.keySet()) {
			Product prod = productService.findBySku(sku);

			prod.getStock().setQuantity(map.get(sku));
			productRepository.save(prod);
		}
		
		
		return sale;
	}

	private void validateStock() {

		map.clear();

		for (Product productCart : cartShoppingService.findAll()) {
			Integer quantityInStock = productService.findBySku(productCart.getSku()).getStock().getQuantity();

			if (quantityInStock < productCart.getStock().getQuantity()) {
				throw new IllegalArgumentException("The quantity in the cart shopping exceeds stock");
			}

			// Quantidades para serem atualizadas

			Integer stock = productService.findBySku(productCart.getSku()).getStock().getQuantity();
			Integer cart = productCart.getStock().getQuantity();

			// Armazenado valores para atualização do estoque
			map.put(productCart.getSku(), (stock - cart));
		}
	}
}