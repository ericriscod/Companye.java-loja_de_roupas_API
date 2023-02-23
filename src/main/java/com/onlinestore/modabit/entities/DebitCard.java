package com.onlinestore.modabit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class DebitCard extends PaymentMethod {

	private String cardNumber;

	@Transient
	private String securityCode;
	
	public DebitCard() {
	}

	public DebitCard(String cardNumber, String securityCode) {
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
