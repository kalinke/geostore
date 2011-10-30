package br.com.geostore.activities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.com.geostore.entity.Empresa;
import br.com.geostore.entity.Produto;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Rota extends MapActivity {
	//variavel de controle para debbug LogCat
	private static final String CATEGORIA = "livro";
	
	private static final String URL_SERVLET = "http://192.168.0.104:8080/GeoStore/BuscarCoord";
	
	private static final long dist_atualiz = 5; // em metros
	private static final long tempo_atualiz = 10000; // em milisegundos
	protected LocationManager locationManager;
	private MapController controlador;
	protected Button atualizaposicao;	
	//double latcouto = -25.421028;
	//double longcouto = -49.259616;
	MapView mapa;
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.mapa_tela); 

		mapa = (MapView) findViewById(R.id.myMapView1);
		atualizaposicao = (Button) findViewById(R.id.pos_atual);		
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//local pelo GPS
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tempo_atualiz, dist_atualiz, new MyLocationListener());
		//local pela rede de dados
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, tempo_atualiz, dist_atualiz, new MyLocationListener());

		atualizaposicao.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				GeoPoint aqui = mostraPosicaoAtual();								
				GeoPoint GeoPoint_dest = mostraCoordDestFixa();												
				RotaDraw(aqui, GeoPoint_dest, Color.BLUE, mapa); 
				mapa.getController().animateTo(aqui); 
				mapa.getController().setZoom(15); 
				mapa.getController().setCenter(aqui);        
				mapa.setBuiltInZoomControls(true);
				mapa.invalidate();
			}			
		});	
		
		GeoPoint GeoPoint_orig = mostraCoordOrigFixa(); 		
		GeoPoint GeoPoint_dest = mostraCoordDestFixa();
		
		RotaDraw(GeoPoint_orig, GeoPoint_dest, Color.GREEN, mapa); 
		mapa.getController().animateTo(GeoPoint_orig); 
		mapa.getController().setZoom(15); 
		mapa.getController().setCenter(GeoPoint_orig);        
		mapa.setBuiltInZoomControls(true);
		mapa.invalidate();
	} 

	protected GeoPoint mostraPosicaoAtual() {
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {
			String message = String.format("Posicao Atual \n Longitude: %1$s \n Latitude: %2$s", location.getLongitude(), location.getLatitude());
			Toast.makeText(Rota.this, message, Toast.LENGTH_LONG).show();
		}
		else{
			Log.i(CATEGORIA,"mostraposicao GPS retornou location null");
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}	
		GeoPoint atual = new GeoPoint((int) (location.getLatitude() * 1E6),(int) (location.getLongitude() * 1E6));		
		return atual;
	}
	
	private GeoPoint mostraDestino() {
		Log.i(CATEGORIA,"entrou no mostraDestino");
		double lat_dest = -25.495890; // Rua General Potiguara, 200 
		double long_dest = -49.308829;
		GeoPoint destino = new GeoPoint((int) (lat_dest * 1E6),(int) (long_dest * 1E6));
		return destino;
	}
	
	private GeoPoint mostraCoordOrigFixa() {
		//Log.i(CATEGORIA,"entrou no mostraDestino");
		double lat_orig = -25.472878; // Rua Alfredo José Pinto, 1640 
		double long_orig = -49.326038;;
		GeoPoint coordfixorig = new GeoPoint((int) (lat_orig * 1E6),(int) (long_orig * 1E6));
		return coordfixorig;
	}
	
	private GeoPoint mostraCoordDestFixa() {
		//Log.i(CATEGORIA,"entrou no mostraDestino");
		
		//parte nova
		
		//conectando ao servlet
		URL url;
		try {
			url = new URL(URL_SERVLET);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			ObjectInputStream ois = new ObjectInputStream(conn.getInputStream());					
			Empresa e = (Empresa) ois.readObject();
			ois.close();
			
			double lat = e.getEndereco().getLatitude();
			double longt = e.getEndereco().getLongitude();
			
			GeoPoint coordfixdest = new GeoPoint((int) (lat * 1E6),(int) (longt * 1E6));
			return coordfixdest;
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
		
		
		
	}

	protected boolean isRouteDisplayed() { 	 
		return false; 
	} 

	private void RotaDraw(GeoPoint orig,GeoPoint dest, int color, MapView mMapView01){ 
		// monta o endereço para baixar o arquivo kml da rota 
		StringBuilder urlString = new StringBuilder(); 
		urlString.append("http://maps.google.com/maps?f=d&hl=en"); 
		urlString.append("&saddr=");//from 
		urlString.append( Double.toString((double)orig.getLatitudeE6()/1.0E6 )); 
		urlString.append(","); 
		urlString.append( Double.toString((double)orig.getLongitudeE6()/1.0E6 )); 
		urlString.append("&daddr=");//to 
		urlString.append( Double.toString((double)dest.getLatitudeE6()/1.0E6 )); 
		urlString.append(","); 
		urlString.append( Double.toString((double)dest.getLongitudeE6()/1.0E6 )); 
		urlString.append("&ie=UTF8&0&om=0&output=kml"); 

		Log.d("xxx","URL="+urlString.toString()); 

		//pega o arquivo kml da rota e retira as coordenadas para determinar a rota		
		Document doc = null; 
		HttpURLConnection urlConnection= null; 
		URL url = null; 
		try 
		{ 
			url = new URL(urlString.toString()); 
			urlConnection=(HttpURLConnection)url.openConnection(); 
			urlConnection.setRequestMethod("GET"); 
			urlConnection.setDoOutput(true); 
			urlConnection.setDoInput(true); 
			urlConnection.connect(); 

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			doc = db.parse(urlConnection.getInputStream()); 


			if(doc.getElementsByTagName("GeometryCollection").getLength()>0){ 
				//String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName(); 
				String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue() ; 
				Log.d("xxx","path="+ path); 
				String [] pairs = path.split(" "); 
				String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height 
				// src 
				GeoPoint startGP = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6)); 
				mMapView01.getOverlays().add(new Camada(startGP,startGP,1)); 
				GeoPoint gp1; 
				GeoPoint gp2 = startGP; 
				for(int i=1;i<pairs.length;i++) // the last one would be crash 
				{ 
					lngLat = pairs[i].split(","); 
					gp1 = gp2; 
					// watch out! For GeoPoint, first:latitude, second:longitude 
					gp2 = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6)); 
					mMapView01.getOverlays().add(new Camada(gp1,gp2,2,color)); 
					Log.d("xxx","pair:" + pairs[i]); 
				} 
				mMapView01.getOverlays().add(new Camada(dest,dest, 3)); // use the default color 
			} 
		} 
		catch (MalformedURLException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (ParserConfigurationException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (SAXException e) 
		{ 
			e.printStackTrace(); 
		} 
	}

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {

			GeoPoint point = new Coordenada(location);

			Log.i(CATEGORIA,"coordenadas= "+ point);
			//parte nova
			String message = String.format(
					"Novo Local \n Longitude: %1$s \n Latitude: %2$s", location.getLongitude(), location.getLatitude());

			Toast.makeText(Rota.this, message, Toast.LENGTH_LONG).show();
		}

		public void onStatusChanged(String s, int i, Bundle b) {
			Toast.makeText(Rota.this, "Provider status changed",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderDisabled(String s) {
			Toast.makeText(Rota.this,
					"GPS Desligado!",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderEnabled(String s) {
			Toast.makeText(Rota.this,
					"GPS Ligado!",
					Toast.LENGTH_LONG).show();
		}
	}
}

