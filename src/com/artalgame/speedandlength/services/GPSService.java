package com.artalgame.speedandlength.services;


import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

public class GPSService extends Service {
	private final IBinder binder = new GPSBinder();
	
	public boolean isServiceStarted = false;
	public boolean isPlay = false;
	public boolean isStop = false;
	public boolean isPause = false;
	
	private LocationManager locationManager;
	private LocationProvider locationProvider;
	private LocationListener locationListener;
	private String bestProvider;
	
	private int minTime = 1000; //miliseconds
	private int minDistance = 1;//meters
	private Location lastLocation;
	private float currentSpeed;
	private float currentDistance;
	private float bearing;
	
	private Listener gpsListener;

	protected Iterable<GpsSatellite> sats;

	private Criteria criteria;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		if(!isServiceStarted){
			isServiceStarted = true;
			
			locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
			gpsListener = new GpsStatus.Listener() {
				
				@Override
				public void onGpsStatusChanged(int event) {
					// TODO Auto-generated method stub
					 if( event == GpsStatus.GPS_EVENT_SATELLITE_STATUS){
			                sats = locationManager.getGpsStatus(null).getSatellites();
			                
			            }
				}
			};
			locationManager.addGpsStatusListener(gpsListener);
			criteria = getGPSCriteria();
		}
		return startId;
	}

	public Iterable<GpsSatellite> getSatelites()
	{
		return sats;
	}
	private LocationListener  getLocationListener() {
		return new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onLocationChanged(Location location) {
				if(isPlay)
				{
					long deltaTime = location.getTime() - lastLocation.getTime();
					currentSpeed = location.getSpeed();
					currentDistance += currentSpeed * deltaTime / 1000;
					bearing = location.bearingTo(lastLocation);
					lastLocation = location;
				}
				GpsStatus status = locationManager.getGpsStatus(null); 
			}
			
		};
	}

	private Criteria getGPSCriteria() {
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setSpeedRequired(true);
		criteria.setCostAllowed(true);
		return criteria;
	}
	
	public void setPlay(boolean isPlay){
		if(isPlay && !isPause)
		{
			bestProvider = locationManager.getBestProvider(criteria, true);
			locationProvider = locationManager.getProvider(bestProvider);
			locationListener = getLocationListener(); 
			locationManager.requestLocationUpdates(bestProvider, minTime, minDistance, locationListener);
			lastLocation = locationManager.getLastKnownLocation(bestProvider);
		}
		else
			if(isPause)
			{
				locationManager.requestLocationUpdates(bestProvider, minTime, minDistance, locationListener);
			}
		setStop(false);
		setPause(false);
		this.isPlay = isPlay;
	}
	
	public boolean getPlay(){
		return isPlay;
	}
	
	public void setPause(boolean b) {
		if(b)
		{
			isPlay = false;
			isPause = true;
			locationManager.removeUpdates(locationListener);
		}
		
	}
	public void setStop(boolean b) {
		// TODO Auto-generated method stub
		if(b)
		{
			isPlay = false;
			isPause = false;
			isStop = true;
			currentDistance = 0;
			currentSpeed = 0;
			locationManager.removeUpdates(locationListener);
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return binder;
	}
	
	public class GPSBinder extends Binder {
		public GPSService getService(){
			return GPSService.this;
		}
	}

	private double d2r = Math.PI / 180.0;

	private double haversine_km(double lat1, double long1, double lat2, double long2)
	{
	    double dlong = (long2 - long1) * d2r;
	    double dlat = (lat2 - lat1) * d2r;
	    double a = Math.pow(Math.sin(dlat/2.0), 2) + Math.cos(lat1*d2r) * Math.cos(lat2*d2r) * Math.pow(Math.sin(dlong/2.0), 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double d = 6367 * c * 1000;	
	    return d;
	}
	public CharSequence getCurrentSpeedToString() {
		// TODO Auto-generated method stub
		return Double.toString(currentSpeed);
	}
	public CharSequence getCurrentDistanceToString() {
		// TODO Auto-generated method stub
		return Double.toString(currentDistance);
	}
	public double getCurrentSpeed() {
		// TODO Auto-generated method stub
		return currentSpeed;
	}
	public double getCurrentDistance() {
		// TODO Auto-generated method stub
		return currentDistance;
	}

	public float getBearing() {
		// TODO Auto-generated method stub
		return bearing;
	}
}
