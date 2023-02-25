package com.onlinestore.modabit.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cartshopping")
public class CartShopping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount() {
		amount = 0;
		for (Product prod : products) {
			amount += prod.getStock().getQuantity();
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice() {
		price = 0d;
		for (Product prod : products) {
			price += prod.subTotal();
		}
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartShopping other = (CartShopping) obj;
		return id == other.id;
	}

}
