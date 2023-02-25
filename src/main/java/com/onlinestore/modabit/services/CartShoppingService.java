package com.onlinestore.modabit.services;

import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.modabit.entities.CartShopping;
import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.repositories.ProductRepository;

@Service
public class CartShoppingService {

	@Autowired
	private ProductRepository productRepository;

	private static CartShopping cart = new CartShopping();
	
	public CartShopping getCartShopping() {
		return cart;
	}

	// Burcar

	public Set<Product> findAll() {
		return cart.getProducts();
	}

	public Product findById(Long id) {
		if (cart.getProducts().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}
		for (Product product : cart.getProducts()) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		throw new NoSuchElementException("Product not found");

	}

	public Product findBySku(String sku) {
		if (sku.length() != 17) {
			throw new IllegalArgumentException("Invalid sku");
		}

		if (cart.getProducts().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		for (Product product : cart.getProducts()) {

			if (product.getSku().equalsIgnoreCase(sku)) {

				return product;
			}
		}
		throw new NoSuchElementException("Product not found");
	}

	// Inserir

	public Product save(String sku, Integer quantity) {
		if (sku.length() != 17 || quantity <= 0) {
			throw new IllegalArgumentException("Invalid sku or quantity");
		}

		Product product = productRepository.findBySku(sku.toUpperCase())
				.orElseThrow(() -> new IllegalArgumentException("Product not found"));

		// Verificando se já existe

		if (cart.getProducts().contains(product)) {

			throw new RuntimeException("There is already a product with the SKU informed");
		}

		// Inserindo produto no carrinho

		product.getStock().setQuantity(quantity);
		cart.getProducts().add(product);

		return product;
	}

	// atualizando

	public Product update(String sku, Integer quantity) {
		if (sku.length() != 17 || quantity <= 0) {
			throw new IllegalArgumentException("Invalid sku or quantity");
		}

		if (cart.getProducts().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		// buscando do carrinho
		Product updateProduct = findBySku(sku.toUpperCase());

		if (updateProduct == null) {
			throw new IllegalArgumentException("There is not this product in the cart");
		}

		updateProduct.getStock().setQuantity(quantity);

		cart.getProducts().add(updateProduct);
		return updateProduct;
	}

	// remover

	public void removeBySku(String sku) {
		if (sku.length() != 17) {
			throw new IllegalArgumentException("Invalid sku or quantity");
		}

		if (cart.getProducts().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		if (!cart.getProducts().removeIf(prod -> prod.getSku().equalsIgnoreCase(sku))) {
			throw new NoSuchElementException("Product not found");
		}

	}

	public void removeAll() {
		if (cart.getProducts().isEmpty()) {
			throw new NoSuchElementException("Cart Shopping is Empty");
		}

		cart.getProducts().clear();
	}

	//
}
