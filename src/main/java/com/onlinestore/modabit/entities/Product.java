package com.onlinestore.modabit.entities;

import java.io.Serializable;

import com.onlinestore.modabit.enums.CategoryEnum;
import com.onlinestore.modabit.enums.ColorEnum;
import com.onlinestore.modabit.enums.DepartmentEnum;
import com.onlinestore.modabit.enums.SizeEnum;
import com.onlinestore.modabit.enums.TypeEnum;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_product")

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String sku;
	private Double price;	

	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	@Enumerated(EnumType.STRING)
	private SizeEnum size;
	@Enumerated(EnumType.STRING)
	private ColorEnum color;
	@Enumerated(EnumType.STRING)
	private CategoryEnum category;
	@Enumerated(EnumType.STRING)
	private DepartmentEnum department;
	
	@Embedded
	private Stock stock;
	
	public Product(String sku, Double price, Stock stock) {
		this.sku = sku;
		this.price = price;
		this.stock = stock;
		insertEnum();
	}

	public String getSku() {
		return sku.toUpperCase();
	}

	public void insertEnum() {
		sku = sku.toUpperCase();
		category = CategoryEnum.valueEnum(sku.substring(0,4));
		color = ColorEnum.valueEnum(sku.substring(4,8));
		department = DepartmentEnum.valueEnum(sku.substring(8,11));
		type = TypeEnum.valueEnum(sku.substring(11,14));
		size = SizeEnum.valueEnum(sku.substring(14));
		}
	
	public Double subTotal(){
		return price * stock.getQuantity();
	}
}
