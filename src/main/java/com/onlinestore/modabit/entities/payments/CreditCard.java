package com.onlinestore.modabit.entities.payments;

import jakarta.persistence.Entity;

@Entity
public class CreditCard extends PaymentMethod {
	
	private String holder;
	private String cardNumber;
	private String expirationDate;
	private Integer securityCode;

	public CreditCard() {
		super();
	}

	public CreditCard(String holder, String cardNumber, String expirationDate, Integer securityCode) {
		super();
		this.holder = holder;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
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
