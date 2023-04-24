package com.onlinestore.modabit.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pix extends PaymentMethod{
	
	private String keyPix;

	public Pix() {
	}
	
	public Pix(String keyPix) {
		this.keyPix = keyPix;
	}

}
