package com.onlinestore.modabit.entities;

import java.io.Serializable;
import java.util.Objects;

import com.onlinestore.modabit.entities.enums.CategoryEnum;
import com.onlinestore.modabit.entities.enums.ColorEnum;
import com.onlinestore.modabit.entities.enums.DepartmentEnum;
import com.onlinestore.modabit.entities.enums.SizeEnum;
import com.onlinestore.modabit.entities.enums.TypeEnum;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Product() {
	}
	
	public Product(String sku, Double price, Stock stock) {
		this.sku = sku;
		this.price = price;
		this.stock = stock;
		insertEnum();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSku() {
		return sku.toUpperCase();
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public TypeEnum getType() {
		return type;
	}

	public SizeEnum getSize() {
		return size;
	}

	public ColorEnum getColor() {
		return color;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public DepartmentEnum getDepartment() {
		return department;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
