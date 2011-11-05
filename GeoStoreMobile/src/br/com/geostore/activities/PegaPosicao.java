package br.com.geostore.activities;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class PegaPosicao implements LocationListener {

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	/*
	
	public void onLocationChanged(Location location) {

		String message = String.format(	
				"Nova localização \n Longitude: %1$s \n Latitude: %2$s",
				location.getLongitude(), location.getLatitude()
		);
		
		Toast.makeText(LbsGeocodingProjectActivity.this, message, Toast.LENGTH_LONG).show();
	}

	public void onStatusChanged(String s, int i, Bundle b) {

		Toast.makeText(LbsGeocodingProjectActivity.this, "Provider status changed",
				Toast.LENGTH_LONG).show();
	}

	public void onProviderDisabled(String s) {

		Toast.makeText(LbsGeocodingProjectActivity.this,
				"Provider disabled by the user. GPS turned off",
				Toast.LENGTH_LONG).show();
	}

	public void onProviderEnabled(String s) {
		Toast.makeText(LbsGeocodingProjectActivity.this,
				"Provider enabled by the user. GPS turned on",
				Toast.LENGTH_LONG).show();
	}
	
	*/
}

