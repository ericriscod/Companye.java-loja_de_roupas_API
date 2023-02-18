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

	// Burcar

	public Set<Product> findAll() {
		return cart.getProducts();
	}

	public Product findById(Long id) {
		for (Product prod : cart.getProducts()) {
			if (prod.getId().equals(id)) {
				return prod;
			}
		}
		throw new NoSuchElementException("Product not found");
	}

	public Product findBySku(String sku) {
		if (sku.length() != 17) {
			throw new IllegalArgumentException("Invalid sku");
		}

		for (Product prod : cart.getProducts()) {
			if (prod.getSku().equalsIgnoreCase(sku)) {
				return prod;
			}
		}
		throw new NoSuchElementException("Product not found");
	}

	// Inserir

	public Product insertBySku(String sku, Integer quantity) {
		if (sku.length() != 17 || quantity <= 0) {
			throw new IllegalArgumentException("Invalid sku or quantity");
		}

		Product product = productRepository.findBySku(sku.toUpperCase())
				.orElseThrow(() -> new IllegalArgumentException("Product not found"));

		// Verificando se já existe e incrementando caso sim

		if (cart.getProducts().contains(product)) {

			throw new RuntimeException("There is already a product with the SKU informed");
		}

		// Inserindo produto no carrinho

		product.getStock().setQuantity(quantity);
		cart.getProducts().add(product);

		return product;
	}

	// atualizando

	public Product update(Product product) {

		if (product.getPrice() < 0 || product.getStock().getQuantity() <= 0) {
			throw new IllegalArgumentException("Invalid price or quantity");
		}

		// buscando no carrinho
		Product updateProduct = findBySku(product.getSku());

		if (updateProduct == null)
			throw new IllegalArgumentException("No there is the product");

		updateProduct.setPrice(product.getPrice());
		updateProduct.setStock(product.getStock());
		cart.getProducts().add(updateProduct);
		return updateProduct;
	}

	public Product update(String sku, Integer quantity) {
		if (sku.length() != 17 || quantity <= 0) {
			throw new IllegalArgumentException("Invalid sku or quantity");
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

	public void removeBySku(String sku, Integer quantity) {
		if (sku.length() != 17 || quantity <= 0) {
			throw new IllegalArgumentException("Invalid sku or quantity");
		}

		for (Product prod : cart.getProducts()) {
			if (prod.getSku().equalsIgnoreCase(sku)) {

				Integer quantityExistent = prod.getStock().getQuantity();

				if (quantityExistent < quantity) {
					throw new IllegalArgumentException("Invalid quantity");
				} else {

					prod.getStock().setQuantity(quantityExistent -= quantity);
				}
			}
		}
	}

	public void removeAllBySku(String sku) {
		if (sku.length() != 17) {
			throw new IllegalArgumentException("Invalid sku or quantity");
		}

		if (!cart.getProducts().removeIf(product -> product.getSku().equalsIgnoreCase(sku))) {
			throw new NoSuchElementException("Product not found");
		}
	}

	public void removeAll() {
		cart.getProducts().clear();
	}
}
