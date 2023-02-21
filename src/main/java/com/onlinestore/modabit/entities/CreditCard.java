package com.onlinestore.modabit.entities;

import jakarta.persistence.Entity;

@Entity
public class CreditCard extends PaymentMethod{

	private String cardNumber;
    private String expirationDate;
    
	public CreditCard(Double amount, String cardNumber, String expirationDate) {
		super(amount);
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
    
    
}
