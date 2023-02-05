package com.onlinestore.modabit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.repositories.ProductRepository;

@Service
public class StockService {
	
	@Autowired
	private ProductRepository repository;
	
	public Page<Product> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Product findById(Long id) {
		Optional<Product> product = repository.findById(id);
		return product.get();
	}
	
	public Product save(Product product) {
		return repository.save(product);
	}
}
