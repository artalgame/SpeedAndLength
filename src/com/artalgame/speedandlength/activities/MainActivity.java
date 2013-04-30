package com.artalgame.speedandlength.activities;

import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.R.layout;
import com.artalgame.speedandlength.application.SpeedAndLengthApplication;
import com.artalgame.speedandlength.vidgets.GPSButton;
import com.artalgame.speedandlength.vidgets.SettingsButton;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Button settingsButton;
	private Button gpsButton;
	private TextView caption;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		settingsButton = (Button)findViewById(R.id.settingsButton);
		gpsButton = (Button)findViewById(R.id.gpsButton);
		caption = (TextView)findViewById(R.id.titleTextView);
		gpsButton.setOnClickListener(new GPSButton());
		settingsButton.setOnClickListener(new SettingsButton());
	}

}
