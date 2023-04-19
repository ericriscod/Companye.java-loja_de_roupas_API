package com.onlinestore.modabit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = service.findAll();
		if (!products.isEmpty()) {
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.noContent().build();
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
		Page<Product> products = service.findAll(pageable);
		if (!products.isEmpty()) {
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.noContent().build();
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/search-id/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id).get());
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/search-sku/{sku}")
	public ResponseEntity<Product> findBySku(@PathVariable String sku) {
		return ResponseEntity.ok(service.findBySku(sku));
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product saveProduct) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(saveProduct));
	}

	@Transactional
	@PutMapping
	public ResponseEntity<Product> update(@RequestBody Product updateProduct) {
		return ResponseEntity.ok(service.update(updateProduct));
	}

	@Transactional
	@PatchMapping(value = "/price/{sku}/{price}")
	public ResponseEntity<Product> update(@PathVariable(value = "sku") String sku,
			@PathVariable(value = "price") Double price) {
		return ResponseEntity.ok(service.update(sku, price));
	}

	@Transactional
	@PatchMapping(value = "/quantity/{sku}/{quantity}")
	public ResponseEntity<Product> update(@PathVariable(value = "sku") String sku,
			@PathVariable(value = "quantity") Integer quantity) {
		return ResponseEntity.ok(service.update(sku, quantity));
	}

	@Transactional
	@DeleteMapping(value = "/{sku}")
	public void delete(@PathVariable String sku) {
		service.deleteBySku(sku);
	}
}
