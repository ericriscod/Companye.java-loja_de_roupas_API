package com.onlinestore.modabit.entities;

public class CreditCard extends PaymentMethod{

	private String cardNumber;
    private String expirationDate;
    
	public CreditCard(String cpf, String cardNumber, String expirationDate) {
		super(cpf);
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
