package com.onlinestore.modabit.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Boleto extends PaymentMethod{
	
	private LocalDate expirationDate;
	
	public Boleto(Double amount, LocalDate expirationDate) {
		super(amount);
		this.expirationDate = expirationDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
