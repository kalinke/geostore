package br.com.geostore.activities;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public class Coordenada extends GeoPoint{
	
	//valores em graus * 1E6 (microdegrees)
		public Coordenada(int latitudeE6, int longitudeE6) {
			super(latitudeE6, longitudeE6);
		}
		
		//Converte para "graus * 1E6"
		public Coordenada(double latitude, double longitude){
			this((int)(latitude*1E6), (int)(longitude*1E6));
		}
		
		//Cria baseado no objeto 'Location' diretamente recebido do GPS
		public Coordenada(Location location){
			this(location.getLatitude(), location.getLongitude());
		}

}

