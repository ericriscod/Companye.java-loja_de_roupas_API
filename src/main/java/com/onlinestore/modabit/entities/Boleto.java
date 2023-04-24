package com.onlinestore.modabit.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Boleto extends PaymentMethod {

	private LocalDate expirationDate;

	public Boleto() {
	}

	public Boleto(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

}
