package br.com.geostore.activities;

import br.com.geostore.entity.Buscar;
import br.com.geostore.entity.Produto;
import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class GeoStoreActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	private static final String URL_SERVLET = "http://10.0.2.2:8080/GeoStore/BuscarProdutosServlet";
	//private static final String URL_SERVLET = "http://localhost:8080/GeoStoreServlet/BuscarProdutosServlet";
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);        
        btnBuscar.setOnClickListener(this);
        
             
        }

	@Override
	public void onClick(View v) {			
				
		//Coleta a informação que o usuário digitou
		EditText edtBuscar = (EditText) findViewById(R.id.edtBuscar);
		
		//Coleta a coordenada do usuário ???
		Location l = null;//getPosAtual();
		Double lat = null;
		Double log = null;
		if (l!=null){
			lat = l.getLatitude();
			log = l.getLongitude();
		}
					
		//Executa a busca no servidor *** precisa usar thread
		run(edtBuscar.getText().toString(), lat, log);
	}
	
	public void run(String sTexto, Double lat, Double log){
		try {
		    
			//conectando ao servlet
			URL url = new URL(URL_SERVLET);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//monta o objeto da busca
			Buscar b = new Buscar();
			b.setTexto(sTexto);
			b.setLat(lat);
			b.setLog(log);
			
		    //enviando o objeto ao servlet	    
		    conn.setDoOutput(true);
		    conn.setAllowUserInteraction(false);
		    ObjectOutputStream wr = new ObjectOutputStream(conn.getOutputStream());
		    wr.writeObject(b);
		    wr.flush();
		    wr.close();
		    
		    //recebendo a lista de produtos localizados
			ObjectInputStream ois = new ObjectInputStream(conn.getInputStream());					
			ArrayList<Produto> prods = (ArrayList<Produto>) ois.readObject();
			ois.close();
			
			if (prods != null){						
				//envia o controle para outra activity
				Intent it = new Intent(this, ListaDeProdutosActivity.class);
				it.putExtra("produtos", prods);
				startActivity(it);
			}else{
				//a buscar nao retornou nenhum resultado
			}					   
		} catch (MalformedURLException e) {
			Log.e("ERRO",e.getMessage(),e);
		} catch (IOException e) {
			Log.e("ERRO",e.getMessage(),e);		
		} catch (ClassNotFoundException e) {
			Log.e("ERRO",e.getMessage(),e);
		}
	}
	
	private Location getPosAtual(){
		
		//Obtem uma instacia do servico de gerenciador de localizacao
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        //Define como provedor o GPS do aparelho.
        String p = lm.getBestProvider(new Criteria(),true);
        
        //Obtem a localizacao (imediatamente)
        Location l = lm.getLastKnownLocation(p);
		       
        return l;
	}
}