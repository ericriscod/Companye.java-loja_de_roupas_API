package com.onlinestore.modabit.entities;

public class DebitCard extends PaymentMethod {
	
	private String cardNumber;
    private String pin;
    
	public DebitCard(String cpf, String cardNumber, String pin) {
		super(cpf);
		this.cardNumber = cardNumber;
		this.pin = pin;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}
