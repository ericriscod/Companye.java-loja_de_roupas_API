package com.onlinestore.modabit.enums;

import com.onlinestore.modabit.exceptions.EnumTypeException;

public enum DepartmentEnum {

	SOCIAL("SOC"), CASUAL("CAS"), BATH("BAT"), SPORT("SPO");

	private String code;

	private DepartmentEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static DepartmentEnum valueEnum(String typeCode) {
		for (DepartmentEnum type : DepartmentEnum.values()) {
			if (type.getCode().equalsIgnoreCase(typeCode)) {
				return type;
			}
		}
		throw new EnumTypeException("Invalid TypeEnum code");
	}
}
