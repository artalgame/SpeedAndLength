package com.artalgame.speedandlength.CommonComponents.Frequency;

import java.util.ArrayList;

import android.content.res.Resources;

import com.artalgame.speedandlength.CommonComponents.Measure.FrequencyUpdateMeasureValues;
import com.artalgame.speedandlength.application.SpeedAndLengthApplication;

public class FrequencyUpdateValues {
	private ArrayList<FrequencyUpdateValue> values;
	private ArrayList<String> stringValues;
	
	private String resStartName;
	
	public FrequencyUpdateValues(String resStartName){
		this.resStartName = resStartName;
		loadResources();
	}
	
	private void loadResources() {
		loadValues();
		createStringValues();
	}
	
	private void createStringValues() {
		stringValues = new ArrayList<String>();
		FrequencyUpdateMeasureValues frequencyUpdateMeasures = new FrequencyUpdateMeasureValues();
		for(int i = 0; i < values.size(); i++)
		{
			FrequencyUpdateValue curValue = values.get(i);
			String measure = frequencyUpdateMeasures.getValues().get(curValue.getMeasureIndex());
			float koef = frequencyUpdateMeasures.getValuesKoefs()[curValue.getMeasureIndex()];
			float value = curValue.getValue()*koef;
			if( value - (int)value < 0.0001)
			{
				String stringValue = Integer.toString((int)value) + " " + measure;
				stringValues.add(stringValue);
			}
			else
			{
				String stringValue = Float.toString(value) + " " + measure;
				stringValues.add(stringValue);
			}
		}
	}
	
	private void loadValues() {
		values = new ArrayList<FrequencyUpdateValue>();
		int i=0;
		String name;
		while(true){
			name = resStartName + "_" + i;
			String value = getStringResource(name);
			if(value == null) break;
			int intValue = Integer.valueOf(value).intValue();
			name = resStartName +"_display_measure_" + i;
			value = getStringResource(name);
			int measureIndex = Integer.valueOf(value).intValue();
			values.add(new FrequencyUpdateValue(intValue, measureIndex));
			i++;
		}	
	}
	
	public ArrayList<FrequencyUpdateValue> getValues(){
		return values;
	}
	
	public ArrayList<String> getStringValues(){
		return stringValues;
	}
	
	
	private static String getStringResource(String name){
		Resources res = SpeedAndLengthApplication.getInstance().getResources();
		int id = res.getIdentifier(name, "string", "com.artalgame.speedandlength");
		if(id != 0)
		{
			return (String) res.getText(id);
		}
		return null;
	}
}
