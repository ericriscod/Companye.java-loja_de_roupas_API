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
	
	//Buscar

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

	//Salvar
	
	public Product save(Product saveProduct) throws IllegalArgumentException {
		Optional<Product> result = findBySku(saveProduct.getSku());

		if (result.isEmpty()) {
			saveProduct.insertEnum();
			repository.save(saveProduct);
			return saveProduct;
		}
		throw new IllegalArgumentException("There is already a product with the SKU informed: " + saveProduct.getSku());
	}
	
	//Atualizar

	public Product update(Product updateProduct) throws IllegalArgumentException {
		if (updateProduct.getPrice() <= 0 || updateProduct.getStock().getQuantity() < 0) {
			throw new IllegalArgumentException("Invalid price or quantity");
		}

		Product product = repository.findBySku(updateProduct.getSku())
				.orElseThrow(() -> new IllegalArgumentException("No there is the product"));
		
		if(product.getStock().getQuantity() > updateProduct.getStock().getQuantity()) {
			throw new IllegalArgumentException("Invalid quantity for updation");
		}

		product.setPrice(updateProduct.getPrice());
		product.setStock(updateProduct.getStock());
		return repository.save(product);
	}

	public Product update(String sku, Double price) throws IllegalArgumentException {
		if (sku.length() != 17 || price <= 0) {
			throw new IllegalArgumentException("Invalid sku or price or quantity");
		}
		
		Product product = repository.findBySku(sku.toUpperCase())
				.orElseThrow(() -> new IllegalArgumentException("No there is the product"));

		product.setPrice(price);
		return repository.save(product);

	}

}
