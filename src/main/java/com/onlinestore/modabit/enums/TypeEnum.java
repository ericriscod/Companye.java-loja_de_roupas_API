package com.onlinestore.modabit.enums;

import com.onlinestore.modabit.exceptions.EnumTypeException;

public enum TypeEnum {

	CLASSIC("CLA"), NO_CLASSIC("NOC");

	private String code;

	private TypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static TypeEnum valueEnum(String typeCode) {
		for (TypeEnum type : TypeEnum.values()) {
			if (type.getCode().equalsIgnoreCase(typeCode)) {
				return type;
			}
		}
		throw new EnumTypeException("Invalid TypeEnum code");
	}
}
