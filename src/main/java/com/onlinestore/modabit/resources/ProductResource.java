package com.onlinestore.modabit.resources;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@Transactional(readOnly = true)
	@GetMapping(value = "/search-id/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		return service.findById(id).map(product -> ResponseEntity.ok(product))
				.orElse(ResponseEntity.badRequest().build());
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/search-sku/{sku}")
	public ResponseEntity<Product> findBySku(@PathVariable String sku) {
		return service.findBySku(sku).map(product -> ResponseEntity.ok(product))
				.orElse(ResponseEntity.badRequest().build());
	}

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


	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product saveProduct) {
		try {
			Product savedProduct = service.save(saveProduct);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	
	//possível alteração
	@PutMapping
	public ResponseEntity<Product> update(@RequestBody Product updateProduct) {
		try {
			Product product = service.update(updateProduct);
			return ResponseEntity.ok(product);
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();

		}
	}
	
	@PatchMapping(value="/price")
	public ResponseEntity<Product> update(@RequestParam String sku, Double price) {
		try {
			Product product = service.update(sku, price);
			return ResponseEntity.ok(product);
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();

		}
	}
	
	@PatchMapping(value="/quantity")
	public ResponseEntity<Product> update(@RequestParam String sku, Integer quantity) {
		try {
			Product product = service.update(sku, quantity);
			return ResponseEntity.ok(product);
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();

		}
	}
	
	@Transactional
	@DeleteMapping(value ="/{sku}")
	public void delete(@PathVariable String sku) {
		service.deleteBySku(sku);
	}
}
