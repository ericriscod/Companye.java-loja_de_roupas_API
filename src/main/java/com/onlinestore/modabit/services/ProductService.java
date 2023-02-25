package com.onlinestore.modabit.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	// Buscar

	public Page<Product> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Optional<Product> findById(Long id) {

		if (repository.findById(id).isPresent()) {
			return repository.findById(id);
		}

		throw new NoSuchElementException("Product not found");
	}

	public Product findBySku(String sku) {
		if (sku.length() != 17) {
			throw new IllegalArgumentException("Invalid sku");
		}

		Product product = repository.findBySku(sku.toUpperCase())
				.orElseThrow(() -> new NoSuchElementException("Product not found"));
		return product;
	}

	// Salvar
	@Transactional
	public Product save(Product saveProduct) {

		if (saveProduct.getPrice() < 0 || saveProduct.getStock().getQuantity() <= 0) {
			throw new IllegalArgumentException("Invalid price or quantity");
		}

		if (repository.findBySku(saveProduct.getSku()).isEmpty()) {
			saveProduct.insertEnum();
			repository.save(saveProduct);
			return saveProduct;
		}

		throw new NoSuchElementException("There is already a product with the SKU informed");
	}

	// Atualizar no put
	@Transactional
	public Product update(Product updateProduct) {

		if (updateProduct.getPrice() < 0 || updateProduct.getStock().getQuantity() <= 0) {
			throw new IllegalArgumentException("Invalid price or quantity");
		}

		Product product = repository.findBySku(updateProduct.getSku())
				.orElseThrow(() -> new IllegalArgumentException("There is not the product"));

		product.setPrice(updateProduct.getPrice());
		product.setStock(updateProduct.getStock());
		return repository.save(product);
	}

	// Atualizar no patch
	@Transactional
	public Product update(String sku, Double price) {
		if (sku.length() != 17 || price <= 0) {
			throw new IllegalArgumentException("Invalid sku or price");
		}

		Product product = repository.findBySku(sku.toUpperCase())
				.orElseThrow(() -> new IllegalArgumentException("There is not the product"));

		product.setPrice(price);
		return repository.save(product);

	}
	@Transactional
	public Product update(String sku, Integer quantity) {

		if (sku.length() != 17 || quantity <= 0) {
			throw new IllegalArgumentException("Invalid sku or quantity");
		}

		Product product = repository.findBySku(sku.toUpperCase())
				.orElseThrow(() -> new IllegalArgumentException("There is not the product"));

		product.getStock().setQuantity(quantity);
		return repository.save(product);
	}

	// Deletar
	@Transactional
	public void deleteBySku(String sku) {
		if (sku.length() != 17) {
			throw new IllegalArgumentException("Invalid sku");
		}
		if (repository.findBySku(sku).isPresent()) {
			repository.deleteBySku(sku);
			return;
		}
		throw new NoSuchElementException("Product not exist");
	}
}
