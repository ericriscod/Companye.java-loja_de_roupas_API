package com.onlinestore.modabit.entities.dto;

import java.time.LocalDateTime;

import com.onlinestore.modabit.entities.Sale;
import com.onlinestore.modabit.entities.enums.PaymentMethodEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaleDTO {

	private PaymentMethodEnum paymentMethod;
	private String cpf;
	private LocalDateTime date;
	private Integer amount;
	private Double price;

	public SaleDTO(Sale sale) {
		cpf = sale.getCpf();
		date = sale.getMoment();
		amount = sale.getCartShopping().getAmount();
		price = sale.getCartShopping().getPrice();
	}
}
