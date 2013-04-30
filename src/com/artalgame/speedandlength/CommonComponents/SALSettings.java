package com.artalgame.speedandlength.CommonComponents;

import android.content.SharedPreferences;

public class SALSettings {
	public static final String speedMeasureSettingName = "speedMeasure";
	public static final String distanceMeasureSettingName = "distanceMeasure";
	public static final String dataUpdateFrequencySettingName = "dataUpdateFrequency";
	public static final String chatUpdateFrequencySettingName = "chatUpdateFrequency";
	
	private String speedMeasure;
	private String distanceMeasure;
	private String dataUpdateFrequency;
	private String chatUpdateFrequency;
	private SharedPreferences sharedPreferences;
	
	public SALSettings(SharedPreferences sharedPreferences){
		this.sharedPreferences = sharedPreferences;
		loadSettings();
	}
	
	public String getSpeedMeasure(){
		return speedMeasure;
	}
	
	public void setSpeedMeasure(String speedMeasure)
	{
		this.speedMeasure = speedMeasure;
	}
	
	public String getDistanceMeasure(){
		return distanceMeasure;
	}
	
	public void setDistanceMeasure(String distanceMeasure){
		this.distanceMeasure = distanceMeasure;
	}
	
	public String getDataUpdateFrequency(){
		return dataUpdateFrequency;
	}
	
	public void setDataUpdateFrequency(String dataUpdateFrequency){
		this.dataUpdateFrequency = dataUpdateFrequency;
	}
	
	public String getChatUpdateFrequency(){
		return chatUpdateFrequency;
	}
	
	public void setChatUpdateFrequency(String chatUpdateFrequency){
		this.chatUpdateFrequency = chatUpdateFrequency;
	}
	
	public void saveSettings(){
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(SALSettings.speedMeasureSettingName, getSpeedMeasure());
		editor.putString(SALSettings.distanceMeasureSettingName, getDistanceMeasure());
		editor.putString(SALSettings.dataUpdateFrequencySettingName, getDataUpdateFrequency());
		editor.putString(SALSettings.chatUpdateFrequencySettingName, getChatUpdateFrequency());
		editor.commit();
	}
	
	private void loadSettings(){
		setSpeedMeasure(sharedPreferences.getString(SALSettings.speedMeasureSettingName, "km/h"));
		setDistanceMeasure(sharedPreferences.getString(SALSettings.distanceMeasureSettingName, "km"));
		setDataUpdateFrequency(sharedPreferences.getString(SALSettings.dataUpdateFrequencySettingName, "1000"));
		setChatUpdateFrequency(sharedPreferences.getString(SALSettings.chatUpdateFrequencySettingName, "5000"));
	}
}
