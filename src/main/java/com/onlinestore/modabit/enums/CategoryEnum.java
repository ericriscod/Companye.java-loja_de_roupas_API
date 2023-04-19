package com.onlinestore.modabit.enums;

import com.onlinestore.modabit.exceptions.EnumTypeException;

public enum CategoryEnum {

	BLOUSE("BLUS"), SHIRTS("CAMI"), SHORTS("SHOR"), PANTS("CALC");

	private String code;

	private CategoryEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static CategoryEnum valueEnum(String typeCode) {
		for (CategoryEnum type : CategoryEnum.values()) {
			if (type.getCode().equalsIgnoreCase(typeCode)) {
				return type;
			}
		}
		throw new EnumTypeException("Invalid TypeEnum code");
	}
}
