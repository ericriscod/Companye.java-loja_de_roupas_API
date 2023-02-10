package com.onlinestore.modabit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.modabit.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
Optional<Product> findBySku(String sku);
}
