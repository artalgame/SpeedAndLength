package com.artalgame.speedandlength.activities;

import java.util.Currency;
import java.util.Timer;
import java.util.TimerTask;

import com.artalgame.speedandlength.GPSService;
import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.CommonComponents.Measure.DistanceMeasureValues;
import com.artalgame.speedandlength.CommonComponents.Measure.SpeedMeasureValues;
import com.artalgame.speedandlength.R.layout;
import com.artalgame.speedandlength.application.SpeedAndLengthApplication;
import com.artalgame.speedandlength.vidgets.GPSButton;
import com.artalgame.speedandlength.vidgets.SettingsButton;

import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.ContactsContract.DataUsageFeedback;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.format.Time;
import android.text.style.UpdateLayout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Button settingsButton;
	private Button gpsButton;
	private Button playButton;
	private Button pauseButton;
	private Button stopButton;
	
	private TextView currentSpeed;
	private TextView currentDistance;
	private GPSService gpsServiceBinder;
	private ServiceConnection gpsServiceConnection;
	private Handler updateDataHandler;
	private TextView caption;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		settingsButton = (Button)findViewById(R.id.settingsButton);
		gpsButton = (Button)findViewById(R.id.gpsButton);
		caption = (TextView)findViewById(R.id.titleTextView);
		
		currentSpeed = (TextView)findViewById(R.id.currentSpeedTextView);
		currentDistance = (TextView)findViewById(R.id.currentDistanceTextView);
		
		gpsButton.setOnClickListener(new GPSButton());
		settingsButton.setOnClickListener(new SettingsButton());
		
		setPlayButton();
		setPauseButton();
		setStopButton();
		
		//set connection with service
		setConnectionWithService();		
		
		//set data update timer
		setDataUpdateHandler();	
	}

	private void setStopButton() {
		
			stopButton = (Button)findViewById(R.id.stopButton);
			stopButton.setEnabled(false);
			stopButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					shareResults();
					v.setEnabled(false);
					playButton.setEnabled(true);
					gpsServiceBinder.setStop(true);
				}
			});
	}
	private void shareResults() {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		double curDistance = gpsServiceBinder.getCurrentDistance();
		String shareString = getString(R.string.share_string) + " " + getStringDistanceUsingSettingMeasure(curDistance) + ". ";
		shareString += getString(R.string.powered_by_string);
		sendIntent.putExtra(Intent.EXTRA_TEXT, shareString);
		sendIntent.setType("text/plain");
		startActivity(sendIntent);
	}

	private void setPauseButton() {
		// TODO Auto-generated method stub
		pauseButton = (Button)findViewById(R.id.pauseButton);
		pauseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gpsServiceBinder.setPause(true);
				v.setEnabled(false);
				playButton.setEnabled(true);
				
			}
		});
	}

	private void setDataUpdateHandler() {
		updateDataHandler = new Handler();
		updateDataHandler.postDelayed(getUpdateDataHandlerTask(), 1000);
	}
	
	private Runnable getUpdateDataHandlerTask(){
		return new Runnable() {
			
			@Override
			public void run() {
				if(gpsServiceBinder != null){
					double curDistance = gpsServiceBinder.getCurrentDistance();
					double curSpeed = gpsServiceBinder.getCurrentSpeed();
					String curDistanceInString = getStringDistanceUsingSettingMeasure(curDistance);
					String curSpeedInString = getStringSpeedUsingSettingMeasure(curSpeed);
					currentDistance.setText(curDistanceInString);
					currentSpeed.setText(curSpeedInString);
				}
				updateDataHandler.postAtTime(this, SystemClock.uptimeMillis() + SpeedAndLengthApplication.settings.getDataUpdateFrequencyAsLong());
			}

			
		};
	}
	
	private String getStringSpeedUsingSettingMeasure(double curSpeed) {
		
		SpeedMeasureValues measureValues = new SpeedMeasureValues();
		int index = SpeedAndLengthApplication.settings.getSpeedMeasureIndex();
		double koef = measureValues.getValuesKoefs()[index];
		String measure = measureValues.getValues().get(index);
		return (curSpeed * koef) + " " + measure;
	}

	private String getStringDistanceUsingSettingMeasure(double currentDistance) {
		
		DistanceMeasureValues measureValues = new DistanceMeasureValues();
		int index = SpeedAndLengthApplication.settings.getDistanceMeasureIndex();
		double koef = measureValues.getValuesKoefs()[index];
		String measure = measureValues.getValues().get(index);
		return (currentDistance * koef) + " " + measure;
	}
	private void setPlayButton() {
		// TODO Auto-generated method stub
		playButton = (Button)findViewById(R.id.playButton);
		playButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gpsServiceBinder.setPlay(true);
				v.setEnabled(false);
				stopButton.setEnabled(true);
				pauseButton.setEnabled(true);
			}
		});
	}

	private void setConnectionWithService() {
		gpsServiceConnection = new ServiceConnection() {
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				gpsServiceBinder = null;
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				gpsServiceBinder = ((GPSService.GPSBinder)service).getService();
			}
		};
		bindService(SpeedAndLengthApplication.getInstance().gpsServiceIntent, gpsServiceConnection, Context.BIND_AUTO_CREATE);
	}
}
