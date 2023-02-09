package com.onlinestore.modabit.entities.enums;

public enum ColorEnum {

	RED("VERM"), GREEN("VERD"), ORANGE("LARA"), YELLOW("AMAR"), BLUE("AZUL"), PURPLE("ROXO"), PINK("ROSA"),
	BROWN("MARR"), BLACK("PRET"), WHITE("BRAN"), GRAY("CINZ");

	private String code;

	private ColorEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static ColorEnum valueEnum(String typeCode) {
		for (ColorEnum type : ColorEnum.values()) {
			if (type.getCode().equalsIgnoreCase(typeCode)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid TypeEnum code");
	}
}
