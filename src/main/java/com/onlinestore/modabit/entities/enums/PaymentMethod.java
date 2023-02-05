package com.onlinestore.modabit.entities.enums;

public enum PaymentMethod {
		PIX(1),
		DEBIT(2),
		CREDIT(3);

	private int code;

	private PaymentMethod(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PaymentMethod valueOf(int code) {
		for (PaymentMethod pay : PaymentMethod.values()) {
			if (pay.getCode() == code) {
				return pay;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
	
	
}
