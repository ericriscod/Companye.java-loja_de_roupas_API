package com.onlinestore.modabit.entities;

import jakarta.persistence.Entity;

@Entity
public class DebitCard extends PaymentMethod {
	
	private String cardNumber;
    private String securityCode;
    
	public DebitCard(Double amount, String cardNumber, String securityCode) {
		super(amount);
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
