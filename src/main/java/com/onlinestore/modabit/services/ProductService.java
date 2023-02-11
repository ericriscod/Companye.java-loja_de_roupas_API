package com.onlinestore.modabit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Page<Product> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Optional<Product> findById(Long id) {
		return repository.findById(id);
	}

	public Optional<Product> findBySku(String sku) {
		return repository.findBySku(sku.toUpperCase());
	}

	public Product save(Product product) throws IllegalArgumentException {
		Optional<Product> result = findBySku(product.getSku());
		
		if (result.isEmpty()) {
			product.insertEnum();
			repository.save(product);
			return product;
		}
		throw new IllegalArgumentException("There is already a product with the SKU informed: " + product.getSku());
	}

	public Product update(Product updateProduct) throws IllegalArgumentException {
		if (updateProduct.getPrice() <= 0 || updateProduct.getStock().getQuantity() < 0) {
			throw new IllegalArgumentException("Invalid price or quantity");
		}
		
		Product product = repository.findBySku(updateProduct.getSku())
				.orElseThrow(() -> new IllegalArgumentException("No there is the product"));
		
		product.setPrice(updateProduct.getPrice());
		product.setStock(updateProduct.getStock());
		return repository.save(product);
	}

}
