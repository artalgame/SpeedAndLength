package com.artalgame.speedandlength.CommonComponents;

public enum ChatFrequencyUpdateEnum implements ISettingsExListData{	
	FIRST(10),
	SECOND(100),
	THIRD(500),
	FORTH(1000);

	private final int value;
	private ChatFrequencyUpdateEnum(int value) {
		
		this.value = value;
	}
	@Override
	public String getStringValue() {
		return Integer.toString(value);
	}
	
}
