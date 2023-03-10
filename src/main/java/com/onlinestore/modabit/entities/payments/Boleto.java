package com.onlinestore.modabit.entities.payments;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Boleto extends PaymentMethod{
	
	private LocalDate expirationDate;
	
	public Boleto() {
	}
	
	public Boleto(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
