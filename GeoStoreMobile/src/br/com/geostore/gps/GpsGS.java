package br.com.geostore.gps;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.maps.GeoPoint;

public class GpsGS extends Activity{
	
	private LocationManager lm;
	
	public GpsGS(LocationManager lm){
		this.lm = lm;
	}
		
	
	public Location getLastLocation(){
		Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (l == null) {			
			l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		return l;
	}
	
	public double getLastLatitude(){
		Location l = this.getLastLocation();
		if (l!=null){
			return l.getLatitude();
		}
		return 0;		
	}
	
	public double getLastLongitude(){
		Location l = this.getLastLocation();
		if (l!=null){
			return l.getLongitude();
		}
		return 0;		
	}
	
	public GeoPoint getLastPosition() {
		Location l = this.getLastLocation();
		if (l!=null){
			return DoubleToGeoPoint(l.getLatitude(), l.getLongitude());
		}
		return null;
	}
	
	public GeoPoint DoubleToGeoPoint(double lat, double log){			
		return new GeoPoint((int) (lat * 1E6), (int) (log * 1E6));
	}
}
