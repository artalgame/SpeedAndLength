package com.artalgame.speedandlength.application;
import com.artalgame.speedandlength.GPSService;
import com.artalgame.speedandlength.CommonComponents.SALSettings;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;

public class SpeedAndLengthApplication extends Application {
	
	private static SpeedAndLengthApplication singleton;
	public static String SETTINGS = "SETTINGS";
	public static SALSettings settings;
	public ComponentName gpsServiceName;
	public Intent gpsServiceIntent;
	public static SpeedAndLengthApplication getInstance() {
		return singleton;
	}
	
	@Override
	public final void onCreate(){
		super.onCreate();
		singleton = this;
		settings = new SALSettings(getSharedPreferences(SETTINGS, MODE_PRIVATE));
		gpsServiceIntent = new Intent(this, GPSService.class);
		gpsServiceName = this.startService(gpsServiceIntent); 
	}
}
