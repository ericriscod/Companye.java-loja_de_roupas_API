package com.onlinestore.modabit.entities.dto;

import java.time.LocalDateTime;

import com.onlinestore.modabit.entities.Sale;
import com.onlinestore.modabit.entities.enums.PaymentMethodEnum;
import com.onlinestore.modabit.entities.payments.Boleto;
import com.onlinestore.modabit.entities.payments.CreditCard;
import com.onlinestore.modabit.entities.payments.DebitCard;
import com.onlinestore.modabit.entities.payments.Pix;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaleDTO {

	private Long id;
	private PaymentMethodEnum paymentMethod;
	private String cpf;
	private LocalDateTime date;
	private Integer amount;
	private Double price;

	public SaleDTO(Sale sale) {
		id = sale.getId();
		cpf = sale.getCpf();
		date = sale.getMoment();
		amount = sale.getCartShopping().getAmount();
		price = sale.getCartShopping().getPrice();

		Class<?> classePayment = sale.getPaymentMethod().getClass();

		if (classePayment == DebitCard.class) {
			paymentMethod = PaymentMethodEnum.DEBITCART;
		} else if (classePayment == CreditCard.class) {
			paymentMethod = PaymentMethodEnum.CREDITCARD;
		} else if (classePayment == Pix.class) {
			paymentMethod = PaymentMethodEnum.PIX;
		} else if (classePayment == Boleto.class) {
			paymentMethod = PaymentMethodEnum.BOLETO;
		}

	}
}
