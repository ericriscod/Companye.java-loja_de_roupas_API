package com.onlinestore.modabit.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.onlinestore.modabit.entities.Product;
import com.onlinestore.modabit.entities.Stock;
import com.onlinestore.modabit.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
	
		
		Stock st1 = new Stock(4);
		Stock st2 = new Stock(10);
		Stock st3 = new Stock(20);		
	
		Product pd1 = new Product(100.0, "Classic", "G", "White", "Blouse", "Social",st1);
		Product pd2 = new Product(100.0, "No-Classic", "GG", "Green", "Shirts", "Esport",st2);
		Product pd3 = new Product(100.0, "Classic", "M", "Grey", "Shorts", "Bath",st3);
		
		productRepository.saveAll(Arrays.asList(pd1,pd2,pd3));	
		
		

	}
}