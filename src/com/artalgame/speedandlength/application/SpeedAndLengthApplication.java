package com.artalgame.speedandlength.application;
import com.artalgame.speedandlength.CommonComponents.SALSettings;

import android.app.Application;

public class SpeedAndLengthApplication extends Application {
	
	private static SpeedAndLengthApplication singleton;
	public static String SETTINGS = "SETTINGS";
	public static SALSettings settings;
	
	public static SpeedAndLengthApplication getInstance() {
		return singleton;
	}
	
	@Override
	public final void onCreate(){
		super.onCreate();
		singleton = this;
		settings = new SALSettings(getSharedPreferences(SETTINGS, MODE_PRIVATE));
	}
}
