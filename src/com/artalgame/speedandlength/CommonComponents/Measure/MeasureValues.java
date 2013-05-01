package com.artalgame.speedandlength.CommonComponents.Measure;

import java.util.ArrayList;

import android.content.res.Resources;
import com.artalgame.speedandlength.application.SpeedAndLengthApplication;

public class MeasureValues {
	public String resStartName;
	private ArrayList<String> values;
	private float[] valuesKoefs;
	private int standartIndex;
	
	public MeasureValues(String startName) {
		this.resStartName = startName;
		loadResources();
	}

	private void loadResources() {
		loadStandartIndex();
		loadValues();
		loadCoefs();
	}
	
	private void loadCoefs() {
		
		valuesKoefs = new float[values.size()];
		String name;
		for(int i = 0; i < valuesKoefs.length; i++){
			name = resStartName + "_koef_" + i;
			String value = getStringResource(name);
			if(value == null)
			{
				break;
			}
			valuesKoefs[i] = Float.valueOf(value);
		}
		
	}

	private void loadValues() {
		values = new ArrayList<String>();
		int i=0;
		String name;
		while(true){
			name = resStartName + "_" + i;
			String value = getStringResource(name);
			if(value == null)
			{
				break;
			}
			values.add(value);
			i++;
		}
	}

	private void loadStandartIndex() {
		
		String name = resStartName + "_as_standart";
		String index = getStringResource(name);
		if(index != null)
		{
			standartIndex = Integer.valueOf(index).intValue();
		}
		else
		{
			standartIndex = 0;
		}
	}

	private String getStringResource(String name){
		Resources res = SpeedAndLengthApplication.getInstance().getResources();
		int id = res.getIdentifier(name, "string", "com.artalgame.speedandlength");
		if(id != 0)
		{
			return (String) res.getText(id);
		}
		return null;
	}
	
	public int getStandartIndex(){
		return standartIndex;
	}
	
	public ArrayList<String> getValues(){
		return values;
	}
	
	public float[] getValuesKoefs(){
		return valuesKoefs;
	}
}
