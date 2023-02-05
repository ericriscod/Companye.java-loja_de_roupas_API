package com.onlinestore.modabit.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Stock {

	private Integer quantity;

	public Stock() {
	}

	public Stock(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
