package com.onlinestore.modabit.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.services.StockService;

@RestController
@RequestMapping(value = "/stock")
public class StockResource {

	@Autowired
	private StockService service;

	@GetMapping(value = "/page")
	public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
		try {
			Page<Product> result = service.findAll(pageable);
			if (!result.isEmpty()) {
				return ResponseEntity.ok(result);
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/{id}")
	public Product productById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product insertProduct(@RequestBody Product product) {
		return service.save(product);
	}
}
