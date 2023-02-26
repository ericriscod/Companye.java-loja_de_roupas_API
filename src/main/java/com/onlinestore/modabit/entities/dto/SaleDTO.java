package com.onlinestore.modabit.entities.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.onlinestore.modabit.entities.Sale;
import com.onlinestore.modabit.entities.enums.PaymentMethodEnum;

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
	}
	
	public SaleDTO(Sale sale, PaymentMethodEnum paymentMethodEnum) {
		id = sale.getId();
		this.paymentMethod = paymentMethodEnum;
		cpf = sale.getCpf();
		date = sale.getMoment();
		amount = sale.getCartShopping().getAmount();
		price = sale.getCartShopping().getPrice();
	}
	
	public SaleDTO(Optional<Sale> sale) {
		id = sale.get().getId();
		cpf = sale.get().getCpf();
		date = sale.get().getMoment();
		amount = sale.get().getCartShopping().getAmount();
		price = sale.get().getCartShopping().getPrice();
	}
	
	public SaleDTO(Optional<Sale> sale, PaymentMethodEnum paymentMethodEnum) {
		id = sale.get().getId();
		this.paymentMethod = paymentMethodEnum;
		cpf = sale.get().getCpf();
		date = sale.get().getMoment();
		amount = sale.get().getCartShopping().getAmount();
		price = sale.get().getCartShopping().getPrice();
	}
}
