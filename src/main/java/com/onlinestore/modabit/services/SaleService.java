package com.onlinestore.modabit.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinestore.modabit.entities.CartShopping;
import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.entities.Sale;
import com.onlinestore.modabit.entities.dto.SaleDTO;
import com.onlinestore.modabit.entities.enums.PaymentMethodEnum;
import com.onlinestore.modabit.entities.payments.PaymentMethod;
import com.onlinestore.modabit.exceptions.NoProductElementException;
import com.onlinestore.modabit.exceptions.ProductArgumentsException;
import com.onlinestore.modabit.exceptions.StockException;
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

	public List<SaleDTO> findAll() {

		List<Sale> sales = saleRepository.findAll();
		List<SaleDTO> salesDTO = new ArrayList<>();

		for (Sale sale : sales) {
			SaleDTO saleDTO = new SaleDTO(sale);
			salesDTO.add(saleDTO);
		}
		return salesDTO;
	}

	public SaleDTO findById(Long id) {

		if (saleRepository.findById(id).isPresent()) {
			return new SaleDTO(saleRepository.findById(id));
		}
		throw new NoProductElementException("Product not found");
	}

	@Transactional
	public SaleDTO validateSale(String cpf, PaymentMethod paymentMethod, PaymentMethodEnum paymentMethodEnum) {

		if (cpf.length() != 11) {
			throw new ProductArgumentsException("Invalid cpf");
		}

		if (cartShoppingService.findAll().isEmpty()) {
			throw new NoProductElementException("Cart Shopping is Empty");
		}

		CartShopping cartShopping = new CartShopping(cartShoppingService.findAll());

		// Validação da quantidade no estoque
		validateStock();
		Sale sale = new Sale(paymentMethod, cpf, LocalDateTime.now(), cartShopping);

		paymentRepository.save(paymentMethod);
		cartRepository.save(cartShopping);
		saleRepository.save(sale);

		// Atualizando estoque
		for (String sku : map.keySet()) {
			Product prod = productService.findBySku(sku);

			prod.getStock().setQuantity(map.get(sku));
			productRepository.save(prod);
		}
		return new SaleDTO(sale, paymentMethodEnum);
	}

	@Transactional
	public SaleDTO validateSale(PaymentMethod paymentMethod, PaymentMethodEnum paymentMethodEnum) {

		if (cartShoppingService.findAll().isEmpty()) {
			throw new NoProductElementException("Cart Shopping is Empty");
		}

		CartShopping cartShopping = new CartShopping(cartShoppingService.findAll());

		// Validação da quantidade no estoque
		validateStock();

		Sale sale = new Sale(paymentMethod, LocalDateTime.now(), cartShopping);

		paymentRepository.save(paymentMethod);
		cartRepository.save(cartShopping);
		saleRepository.save(sale);

		// Atualizando estoque
		for (String sku : map.keySet()) {
			Product prod = productService.findBySku(sku);

			prod.getStock().setQuantity(map.get(sku));
			productRepository.save(prod);
		}
		return new SaleDTO(sale, paymentMethodEnum);
	}

	// Validação de estoque

	private void validateStock() {

		map.clear();

		for (Product productCart : cartShoppingService.findAll()) {
			Integer quantityInStock = productService.findBySku(productCart.getSku()).getStock().getQuantity();

			if (quantityInStock < productCart.getStock().getQuantity()) {
				throw new StockException("The quantity in the cart shopping exceeds stock");
			}

			// Quantidades para serem atualizadas

			Integer stock = productService.findBySku(productCart.getSku()).getStock().getQuantity();
			Integer cart = productCart.getStock().getQuantity();

			// Armazenado valores para atualização do estoque
			map.put(productCart.getSku(), (stock - cart));
		}
	}
}