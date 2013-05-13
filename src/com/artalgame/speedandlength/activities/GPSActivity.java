package com.artalgame.speedandlength.activities;

import java.util.Iterator;

import com.artalgame.speedandlength.GPSService;
import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.application.SpeedAndLengthApplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.TextView;


public class GPSActivity extends Activity {
	private GpsStatus.Listener gpsListener;
	private LocationManager locationManager;
	private TextView satsTextView;
	private ServiceConnection gpsServiceConnection;
	protected GPSService gpsServiceBinder;
	private Handler updateDataHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps_parameters);
		satsTextView = (TextView)findViewById(R.id.satsTextView);
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		setConnectionWithService();
		setDataUpdateHandler();
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
					drawStatus(gpsServiceBinder.getSatelites());
				}
				updateDataHandler.postAtTime(this, SystemClock.uptimeMillis() + SpeedAndLengthApplication.settings.getDataUpdateFrequencyAsLong());
			}
		};
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
	
	private void drawStatus(Iterable<GpsSatellite> sats) {
		if(sats != null){
			int j = 1;
			String name = new String();
			for(Iterator<GpsSatellite> i = sats.iterator(); i.hasNext(); ) {
				  name += j + ")" +i.next().toString()+ "\n";
				  j++;
				}
			satsTextView.setText(name);
		}
		else
		{
			satsTextView.setText(Long.toString(SystemClock.uptimeMillis()));
		}
	}
}
