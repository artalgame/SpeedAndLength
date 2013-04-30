package com.artalgame.speedandlength.CommonComponents;

public enum SpeedMeasureEnum {
	FIRST(0, "km/h"),
	SECOND(1, "m/s");

	private int code;
    private String value;
	private SpeedMeasureEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}
}
