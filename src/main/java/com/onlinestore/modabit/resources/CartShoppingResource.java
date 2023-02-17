package com.onlinestore.modabit.resources;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.services.CartShoppingService;

@RestController
@RequestMapping(value = "/cartshopping")
public class CartShoppingResource {

	@Autowired
	CartShoppingService service;

	@GetMapping
	public ResponseEntity<Set<Product>> findAll() {
		try {
			Set<Product> products = service.findAll();

			if (!products.isEmpty()) {
				return ResponseEntity.ok(products);
			}
			return ResponseEntity.noContent().build();

		} catch (NullPointerException e) {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "/search-sku/{sku}")
	public ResponseEntity<Product> findBySku(String sku) {
		try {
			return ResponseEntity.ok(service.findBySku(sku));
			
		} catch (NullPointerException e) {
			return ResponseEntity.noContent().build();
		}

	}

}
