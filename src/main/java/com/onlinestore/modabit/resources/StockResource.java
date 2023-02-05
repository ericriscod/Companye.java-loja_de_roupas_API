package com.onlinestore.modabit.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value="/stock")
public class StockResource {
	
	@Autowired
	private StockService productService;
	
	@GetMapping
	public List<Product> products() {
		return productService.findAll();
	}
	
	@GetMapping(value= "/{id}")
	public Product productById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product insertProduct(@RequestBody Product product) {
		return productService.save(product);
	}
}
