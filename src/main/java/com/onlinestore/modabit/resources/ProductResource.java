package com.onlinestore.modabit.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

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

}
