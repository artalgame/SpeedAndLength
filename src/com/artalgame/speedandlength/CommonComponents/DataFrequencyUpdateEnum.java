package com.artalgame.speedandlength.CommonComponents;

public enum DataFrequencyUpdateEnum implements ISettingsExListData {
	FIRST(10),
	SECOND(100),
	THIRD(500),
	FORTH(1000);

	private final int value;
	private DataFrequencyUpdateEnum(int value) {
		
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public String getStringValue(){
		return Integer.toString(value);
	}
}
