package com.onlinestore.modabit.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private PaymentMethod paymentMethod;
	
	@OneToOne
	private CartShopping cartShopping;
	
	public Sale() {
	}
	
	public Sale(PaymentMethod paymentMethod, CartShopping cartShopping) {
		super();
		this.paymentMethod = paymentMethod;
		this.cartShopping = cartShopping;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public CartShopping getCartShopping() {
		return cartShopping;
	}

	public void setCartShopping(CartShopping cartShopping) {
		this.cartShopping = cartShopping;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
