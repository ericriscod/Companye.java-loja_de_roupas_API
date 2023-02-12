package com.onlinestore.modabit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.modabit.entities.CartShopping;
import com.onlinestore.modabit.entities.Product;

@Service
public class CartShoppingService {

	@Autowired
	private ProductService productService;

	private static CartShopping cart;

	// Inserir

	public Product insertProductBySku(String sku, Integer quantity) throws IllegalArgumentException {
		if (sku.length() != 17 || quantity <= 0) {
			throw new IllegalArgumentException("Invalid sku or quantity");
		}

		Product insertProduct = productService.findBySku(sku)
				.orElseThrow(() -> new IllegalArgumentException("Error at insert a product"));

		insertProduct.getStock().setQuantity(quantity);

		cart.getProducts().add(insertProduct);

		return insertProduct;
	}
}
