package com.onlinestore.modabit.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
	}
	
	@Transactional(readOnly = true)
	@GetMapping(value = "/sku")
	public ResponseEntity<Product> findBySku(@RequestParam(defaultValue = "") String sku){		
		return service.findBySku(sku).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
	}

	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> result = service.findAll();
		if (!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.noContent().build();
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
		Page<Product> result = service.findAll(pageable);
		if (!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Product> save (@RequestBody Product product) {
		Product result = service.save(product);
		if(result != null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.noContent().build();
	}

}
