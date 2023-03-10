package com.onlinestore.modabit.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_cartshopping")

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartShopping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@EqualsAndHashCode.Include
	private long id;

	private Integer amount;
	private Double price;

	@ManyToMany
	private Set<Product> products = new HashSet<>();

	public CartShopping() {
		setAmount();
		setPrice();
	}

	public CartShopping(Set<Product> products) {
		super();
		this.products = products;
		setAmount();
		setPrice();
	}

	public void setAmount() {
		amount = 0;
		for (Product prod : products) {
			amount += prod.getStock().getQuantity();
		}
	}

	public void setPrice() {
		price = 0d;
		for (Product prod : products) {
			price += prod.subTotal();
		}
	}
}
