package com.artalgame.speedandlength.CommonComponents;

import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.CommonComponents.Frequency.ChartFrequencyUpdateValues;
import com.artalgame.speedandlength.CommonComponents.Frequency.DataFrequencyUpdateValues;
import com.artalgame.speedandlength.CommonComponents.Measure.DistanceMeasureValues;
import com.artalgame.speedandlength.CommonComponents.Measure.SpeedMeasureValues;
import com.artalgame.speedandlength.application.SpeedAndLengthApplication;

import android.content.SharedPreferences;
import android.content.res.Resources;

public class SALSettings {
	//Keys for saving of settings
	public static final String speedMeasureSettingName = "speedMeasure";
	public static final String distanceMeasureSettingName = "distanceMeasure";
	public static final String dataUpdateFrequencySettingName = "dataUpdateFrequency";
	public static final String chartUpdateFrequencySettingName = "chartUpdateFrequency";
	
	public static final String speedMeasureIndexSettingName = "speedMeasureIndex";
	public static final String distanceMeasureIndexSettingName = "distanceMeasureIndex";
	public static final String dataUpdateFrequencyIndexSettingName = "dataUpdateFrequencyIndex";
	public static final String chartUpdateFrequencyIndexSettingName = "chartUpdateFrequencyIndex";
	
	//values of settings
	private String speedMeasure;
	private String distanceMeasure;
	private String dataUpdateFrequency;
	private String chartUpdateFrequency;
	
	private int speedMeasureIndex;
	private int distanceMeasureIndex;
	private int dataUpdateFrequencyIndex;
	private int chartUpdateFrequencyIndex;
	private DataFrequencyUpdateValues dataFrequencyUpdateValues;
	private ChartFrequencyUpdateValues chartFrequencyUpdateValues;
	private SpeedMeasureValues speedMeasureValues;
	private DistanceMeasureValues distanceMeasureValues;
	
	
	private SharedPreferences sharedPreferences;
	
	public SALSettings(SharedPreferences sharedPreferences){
		this.sharedPreferences = sharedPreferences;
		loadSettings();
		InicializeArrays();
	}
	
	private void InicializeArrays() {
		dataFrequencyUpdateValues = new DataFrequencyUpdateValues();
		chartFrequencyUpdateValues = new ChartFrequencyUpdateValues();
		speedMeasureValues = new SpeedMeasureValues();
		distanceMeasureValues = new DistanceMeasureValues();
	}

	public String getSpeedMeasure(){
		return speedMeasure;
	}
	
	public void setSpeedMeasure(String speedMeasure)
	{
		this.speedMeasure = speedMeasure;
	}
	
	public int getSpeedMeasureIndex(){
		return speedMeasureIndex;
	}
	
	public void setSpeedMeasureIndex(int speedMeasureIndex)
	{
		this.speedMeasureIndex = speedMeasureIndex;
	}
	
	public String getDistanceMeasure(){
		return distanceMeasure;
	}
	
	public void setDistanceMeasure(String distanceMeasure){
		this.distanceMeasure = distanceMeasure;
	}
	
	public int getDistanceMeasureIndex(){
		return distanceMeasureIndex;
	}
	
	public void setDistanceMeasureIndex(int distanceMeasureIndex){
		this.distanceMeasureIndex = distanceMeasureIndex;
	}
	
	public String getDataUpdateFrequency(){
		return dataUpdateFrequency;
	}
	public long getDataUpdateFrequencyAsLong(){
		return Long.valueOf(dataFrequencyUpdateValues.getValues().get(dataUpdateFrequencyIndex).getValue());
	}
	
	public void setDataUpdateFrequency(String dataUpdateFrequency){
		this.dataUpdateFrequency = dataUpdateFrequency;
	}
	
	public int getDataUpdateFrequencyIndex(){
		return dataUpdateFrequencyIndex;
	}
	
	public void setDataUpdateFrequencyIndex(int dataUpdateFrequencyIndex){
		this.dataUpdateFrequencyIndex = dataUpdateFrequencyIndex;
	}
	
	public String getChartUpdateFrequency(){
		return chartUpdateFrequency;
	}
	
	public long getChartUpdateFrequencyAsLong(){
		return Long.valueOf(chartFrequencyUpdateValues.getValues().get(chartUpdateFrequencyIndex).getValue());
	}
	
	public void setChartUpdateFrequency(String chartUpdateFrequency){
		this.chartUpdateFrequency = chartUpdateFrequency;
	}
	
	public int getChartUpdateFrequencyIndex(){
		return chartUpdateFrequencyIndex;
	}
	
	public void setChartUpdateFrequencyIndex(int chartUpdateFrequencyIndex){
		this.chartUpdateFrequencyIndex = chartUpdateFrequencyIndex;
	}
	
	public void saveSettings(){
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(SALSettings.speedMeasureSettingName, getSpeedMeasure());
		editor.putString(SALSettings.distanceMeasureSettingName, getDistanceMeasure());
		editor.putString(SALSettings.dataUpdateFrequencySettingName, getDataUpdateFrequency());
		editor.putString(SALSettings.chartUpdateFrequencySettingName, getChartUpdateFrequency());
		
		editor.putInt(SALSettings.speedMeasureIndexSettingName, getSpeedMeasureIndex());
		editor.putInt(SALSettings.distanceMeasureIndexSettingName, getDistanceMeasureIndex());
		editor.putInt(SALSettings.dataUpdateFrequencyIndexSettingName, getDataUpdateFrequencyIndex());
		editor.putInt(SALSettings.chartUpdateFrequencyIndexSettingName, getChartUpdateFrequencyIndex());
		
		editor.commit();
	}
	
	public void cancellNewSettings(){
		loadSettings();
	}
	
	private void loadSettings(){
		int speedMeasureIndex = Integer.valueOf(getStringResource("default_speed_measure_index")).intValue();
		int distanceMeasureIndex = Integer.valueOf(getStringResource("default_distance_measure_index")).intValue();
		int chartUpdateFrequencyIndex = Integer.valueOf(getStringResource("default_chart_frequency_update_index")).intValue();
		int dataUpdateFrequencyIndex = Integer.valueOf(getStringResource("default_data_frequency_update_index")).intValue();
		
		setSpeedMeasure(sharedPreferences.getString(SALSettings.speedMeasureSettingName, new SpeedMeasureValues().getValues().get(speedMeasureIndex)));
		setDistanceMeasure(sharedPreferences.getString(SALSettings.distanceMeasureSettingName, new DistanceMeasureValues().getValues().get(distanceMeasureIndex)));
		setDataUpdateFrequency(sharedPreferences.getString(SALSettings.dataUpdateFrequencySettingName,Integer.toString(new DataFrequencyUpdateValues().getValues().get(dataUpdateFrequencyIndex).getValue())));
		setChartUpdateFrequency(sharedPreferences.getString(SALSettings.chartUpdateFrequencySettingName,Integer.toString(new ChartFrequencyUpdateValues().getValues().get(chartUpdateFrequencyIndex).getValue())));
		
		setSpeedMeasureIndex(sharedPreferences.getInt(SALSettings.speedMeasureIndexSettingName, speedMeasureIndex));
		setDistanceMeasureIndex(sharedPreferences.getInt(SALSettings.distanceMeasureIndexSettingName, distanceMeasureIndex));
		setDataUpdateFrequencyIndex(sharedPreferences.getInt(SALSettings.dataUpdateFrequencyIndexSettingName,dataUpdateFrequencyIndex));
		setChartUpdateFrequencyIndex(sharedPreferences.getInt(SALSettings.chartUpdateFrequencyIndexSettingName,chartUpdateFrequencyIndex));
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
