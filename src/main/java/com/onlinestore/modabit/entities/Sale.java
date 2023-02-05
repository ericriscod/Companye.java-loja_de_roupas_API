package com.onlinestore.modabit.entities;

import java.io.Serializable;
import java.util.Objects;

import com.onlinestore.modabit.entities.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sale implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Order order;
	private String cpf;
	private String keyPix;
	private int payment;
	private Double totalPrice;
	
	public Sale() {
	}

	public Sale(Long id, Order order, String cpf, String keyPix, PaymentMethod payment, Double totalPrice) {
		this.id = id;
		this.order = order;
		this.cpf = cpf;
		this.keyPix = keyPix;
		setPayment(payment);
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getKeyPix() {
		return keyPix;
	}

	public void setKeyPix(String keyPix) {
		this.keyPix = keyPix;
	}

	public PaymentMethod getPayment() {
		return PaymentMethod.valueOf(payment);
	}

	public void setPayment(PaymentMethod payment) {
		if(payment != null) {
			this.payment = payment.getCode();
		}
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double price) {
		this.totalPrice = price;
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
