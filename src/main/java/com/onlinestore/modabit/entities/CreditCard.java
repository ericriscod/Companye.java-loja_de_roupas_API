package com.onlinestore.modabit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class CreditCard extends PaymentMethod {

	private String cardNumber;
	private String expirationDate;
	
	@Transient
	private Integer securityCode;
	
	public CreditCard() {
	}

	public CreditCard(String cardNumber, String expirationDate, Integer securityCode) {
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
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

	public Integer getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}

}
