package com.onlinestore.modabit.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class CartShopping implements Serializable {
	private static final long serialVersionUID = 1L;

	private Set<Product> products;

	private Double price;
	private Integer quantity;

	public CartShopping() {
	}

	public CartShopping(Set<Product> products, Double price, Integer quantity) {
		this.products = products;
		this.price = price;
		this.quantity = quantity;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(products);
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
		return Objects.equals(products, other.products);
	}
	
	

}
