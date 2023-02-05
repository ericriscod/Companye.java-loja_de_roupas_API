package com.onlinestore.modabit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.modabit.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
