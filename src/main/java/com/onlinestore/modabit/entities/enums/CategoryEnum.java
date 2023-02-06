package com.onlinestore.modabit.entities.enums;

public enum CategoryEnum {

	BLOUSE("BLUS"), SHIRTS("CAMI"), SHORTS("SHOR"), PANTS("CALC");

	private String code;

	private CategoryEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public TypeEnum valueEnum(String typeCode) {
		for (TypeEnum type : TypeEnum.values()) {
			if (type.getCode().equalsIgnoreCase(typeCode)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid TypeEnum code");
	}
}
