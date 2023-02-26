package com.onlinestore.modabit.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.modabit.entities.CartShopping;
import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.exceptions.ProductArgumentsException;
import com.onlinestore.modabit.exceptions.NoProductElementException;
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
			throw new NoProductElementException("Cart Shopping is Empty");
		}
		for (Product product : cart.getProducts()) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		throw new NoProductElementException("Product not found");
	}

	public Product findBySku(String sku) {
		if (sku.length() != 17) {
			throw new ProductArgumentsException("Invalid sku");
		}

		if (cart.getProducts().isEmpty()) {
			throw new NoProductElementException("Cart Shopping is Empty");
		}

		for (Product product : cart.getProducts()) {

			if (product.getSku().equalsIgnoreCase(sku)) {

				return product;
			}
		}
		throw new NoProductElementException("Product not found");
	}

	// Inserir

	public Product save(String sku, Integer quantity) {
		if (sku.length() != 17 || quantity <= 0) {
			throw new ProductArgumentsException("Invalid sku or quantity");
		}

		Product product = productRepository.findBySku(sku.toUpperCase())
				.orElseThrow(() -> new NoProductElementException("Product not found"));

		// Verificando se já existe

		if (cart.getProducts().contains(product)) {

			throw new ProductArgumentsException("There is already a product with the SKU informed");
		}

		// Inserindo produto no carrinho

		product.getStock().setQuantity(quantity);

		cart.getProducts().add(product);
		cart.setAmount();
		cart.setPrice();

		return product;
	}

	// atualizando

	public Product update(String sku, Integer quantity) {
		if (sku.length() != 17 || quantity <= 0) {
			throw new ProductArgumentsException("Invalid sku or quantity");
		}

		if (cart.getProducts().isEmpty()) {
			throw new NoProductElementException("Cart Shopping is Empty");
		}

		// buscando do carrinho
		Product updateProduct = findBySku(sku.toUpperCase());

		if (updateProduct == null) {
			throw new ProductArgumentsException("There is not this product in the cart");
		}

		updateProduct.getStock().setQuantity(quantity);

		cart.getProducts().add(updateProduct);
		cart.setAmount();
		cart.setPrice();

		return updateProduct;
	}

	// remover

	public void removeBySku(String sku) {
		if (sku.length() != 17) {
			throw new ProductArgumentsException("Invalid sku or quantity");
		}

		if (cart.getProducts().isEmpty()) {
			throw new NoProductElementException("Cart Shopping is Empty");
		}

		if (!cart.getProducts().removeIf(prod -> prod.getSku().equalsIgnoreCase(sku))) {
			throw new ProductArgumentsException("Product not found");
		}
	}

	public void removeAll() {
		if (cart.getProducts().isEmpty()) {
			throw new NoProductElementException("Cart Shopping is Empty");
		}
		cart.getProducts().clear();
	}

	//
}
