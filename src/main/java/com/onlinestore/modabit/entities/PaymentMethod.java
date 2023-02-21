package com.onlinestore.modabit.entities;

public abstract class PaymentMethod  {
	
	private String  cpf;

	public PaymentMethod(String cpf) {
		super();
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
