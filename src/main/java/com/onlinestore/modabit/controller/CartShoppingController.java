package com.onlinestore.modabit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.modabit.entities.CartShopping;
import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.service.CartShoppingService;

@RestController
@RequestMapping(value = "/cartshopping")
public class CartShoppingController {

	@Autowired
	CartShoppingService service;

	@GetMapping
	public ResponseEntity<CartShopping> getCardShopping() {
		CartShopping cart = service.getCartShopping();

		if (cart.getProducts().isEmpty()) {
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.ok(cart);
	}

	@GetMapping(value = "/search-id/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping(value = "/search-sku/{sku}")
	public ResponseEntity<Product> findBySku(@PathVariable String sku) {
		return ResponseEntity.ok(service.findBySku(sku));
	}

	@PostMapping(value = "/{sku}/{quantity}")
	public ResponseEntity<Product> insertBySku(@PathVariable(value = "sku") String sku,
			@PathVariable(value = "quantity") Integer quantity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(sku, quantity));
	}

	@PatchMapping(value = "/{sku}/{quantity}")
	public ResponseEntity<Product> update(@PathVariable(value = "sku") String sku,
			@PathVariable(value = "quantity") Integer quantity) {
		try {
			Product product = service.update(sku, quantity);
			return ResponseEntity.ok(product);

		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().build();

		}
	}

	@DeleteMapping(value = "/{sku}")
	public void removeBySku(@PathVariable String sku) {
		service.removeBySku(sku);
	}

	@DeleteMapping
	public void removeAllBySku() {
		service.removeAll();
	}

}
