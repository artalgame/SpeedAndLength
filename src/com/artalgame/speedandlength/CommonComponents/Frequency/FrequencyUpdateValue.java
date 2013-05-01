package com.artalgame.speedandlength.CommonComponents.Frequency;

public class FrequencyUpdateValue {
	private int value;
	private int measureIndex;
	
	public FrequencyUpdateValue(int value, int measureIndex){
		
		this.measureIndex = measureIndex;
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	public void setValue(int value){
		this.value = value;
	}
	
	public int getMeasureIndex(){
		return measureIndex;
	}
	public void setMeasureIndex(int measureIndex){
		this.measureIndex = measureIndex;
	}
}
