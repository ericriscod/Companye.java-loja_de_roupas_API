package com.onlinestore.modabit.entities.payments;

public class Pix extends PaymentMethod{
	
	private String keyPix;

	public Pix() {
	}
	
	public Pix(String keyPix) {
		this.keyPix = keyPix;
	}

	public String getKeyPix() {
		return keyPix;
	}

	public void setKeyPix(String keyPix) {
		this.keyPix = keyPix;
	}
	
	

}
