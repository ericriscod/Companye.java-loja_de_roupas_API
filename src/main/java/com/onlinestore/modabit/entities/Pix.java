package com.onlinestore.modabit.entities;

public class Pix extends PaymentMethod{
	
	private String keyPix;

	public Pix(Double amount, String keyPix) {
		super(amount);
		this.keyPix = keyPix;
	}

	public String getKeyPix() {
		return keyPix;
	}

	public void setKeyPix(String keyPix) {
		this.keyPix = keyPix;
	}
	
	

}
