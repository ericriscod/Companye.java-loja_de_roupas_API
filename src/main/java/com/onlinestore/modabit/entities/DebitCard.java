package com.onlinestore.modabit.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DebitCard extends PaymentMethod {

	private String holder;
	private String cardNumber;
	private LocalDate expirationDate;
	private Integer securityCode;

	public DebitCard() {
		super();
	}

	public DebitCard(String holder, String cardNumber, LocalDate expirationDate, Integer securityCode) {
		super();
		this.holder = holder;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}

}
