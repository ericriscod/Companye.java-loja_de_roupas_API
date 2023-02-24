package com.onlinestore.modabit.entities.payments;

import jakarta.persistence.Entity;

@Entity
public class DebitCard extends PaymentMethod {

	private String cardNumber;

	private Integer securityCode;
	
	public DebitCard() {
	}

	public DebitCard(String cardNumber, Integer securityCode) {
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}

}
