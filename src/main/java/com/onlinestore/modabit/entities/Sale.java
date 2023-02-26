package com.onlinestore.modabit.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.onlinestore.modabit.entities.payments.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_sale")

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@OneToOne
	private PaymentMethod paymentMethod;
	
	private String cpf;
	private LocalDateTime moment;

	@OneToOne
	private CartShopping cartShopping;

	public Sale(PaymentMethod paymentMethod, LocalDateTime moment, CartShopping cartShopping) {
		super();
		this.paymentMethod = paymentMethod;
		this.moment = moment;
		this.cartShopping = cartShopping;
	}

	public Sale(PaymentMethod paymentMethod, String cpf, LocalDateTime moment, CartShopping cartShopping) {
		super();
		this.paymentMethod = paymentMethod;
		this.cpf = cpf;
		this.moment = moment;
		this.cartShopping = cartShopping;
	}
}
