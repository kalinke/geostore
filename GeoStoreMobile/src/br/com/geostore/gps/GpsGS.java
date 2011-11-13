package br.com.geostore.gps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;

public class GpsGS{
	
	private static final String TAG = "GpsGS";
	private Context ctx = null;
	private Location l = null;
	
	public GpsGS(Context ctx){
		this.ctx = ctx;		
	}
	
	
	
	public Location getLastLocation(){		
		LocationManager lm  = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);		
		String provider =  LocationManager.GPS_PROVIDER;
		int minTime = 10000;
		int minDistance = 10;
			
		if(lm.isProviderEnabled(provider)){
			
			l = lm.getLastKnownLocation(provider);			
		
		}else{ 
			provider = LocationManager.NETWORK_PROVIDER;
			
			if (lm.isProviderEnabled(provider)){
				
				l = lm.getLastKnownLocation(provider);
				
			}
		}
		
		lm.requestLocationUpdates(provider, minTime, minDistance, new LocationListener() {
			
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
				// TODO Auto-generated method stub
				
			}
		});
		
		return l;
	}		
	
	public double getLastLatitude(){
		this.getLastLocation();
		if (l!=null){
			return l.getLatitude();
		}
		return 0;		
	}
	
	public double getLastLongitude(){
		this.getLastLocation();
		if (l!=null){
			return l.getLongitude();
		}
		return 0;		
	}
	
	public GeoPoint getLastPosition() {
		this.getLastLocation();
		if (l!=null){
			return DoubleToGeoPoint(l.getLatitude(), l.getLongitude());
		}
		return null;
	}
	
	public GeoPoint DoubleToGeoPoint(double lat, double log){			
		return new GeoPoint((int) (lat * 1E6), (int) (log * 1E6));
	}	
}
