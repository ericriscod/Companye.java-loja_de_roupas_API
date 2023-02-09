package com.onlinestore.modabit.entities.enums;

public enum SizeEnum {

	P("PEQ"), M("MED"), G("GRA"), GG("GIG");

	private String code;

	private SizeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static SizeEnum valueEnum(String typeCode) {
		for (SizeEnum type : SizeEnum.values()) {
			if (type.getCode().equalsIgnoreCase(typeCode)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid TypeEnum code");
	}
}
