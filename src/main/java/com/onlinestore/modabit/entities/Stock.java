package com.onlinestore.modabit.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	
	private Integer quantity;
}
