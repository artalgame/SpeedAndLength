package com.artalgame.speedandlength.CommonComponents;

public enum DistanceMeasureEnum {
	FIRST(0, "km"),
	SECOND(1, "m");

	private int code;
    private String value;
	private DistanceMeasureEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}
}
