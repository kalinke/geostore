package br.com.geostore.activities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.geostore.entity.Endereco;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.Produto;
import br.com.geostore.http.HttpGS;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GeoStoreActivity extends Activity implements OnClickListener{    	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_busca_tela);        
        Button btnBuscar = (Button) findViewById(R.id.btBuscarMainBusca);        
        btnBuscar.setOnClickListener(this);
                 
    }

	@Override
	public void onClick(View v) {			
				
		EditText edtBuscar = (EditText) findViewById(R.id.etItemMainBusca);
		EditText edtRaio = (EditText) findViewById(R.id.etRaioMainBusca);
		String log = "0";
		String lat = "0";
		
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		String locationProvider = LocationManager.GPS_PROVIDER;
		Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
		
		if (lastKnownLocation != null){
			log = Double.toString(lastKnownLocation.getLongitude());
			lat = Double.toString(lastKnownLocation.getLatitude());
		}
		
		HttpGS h = new HttpGS();
		String s = h.buscarProdutos(edtBuscar.getText().toString(), log, lat, edtRaio.getText().toString());
		
		Log.i("Retorno", s);
		
		if (s != null){			
			try {
				JSONObject jObj = new JSONObject(s);
				JSONArray jArray = jObj.getJSONArray("produtos");
				ArrayList<Produto> pList = new ArrayList<Produto>();
				
				for (int i=0;i<jArray.length();i++){
					JSONObject j = jArray.getJSONObject(i).getJSONObject("produto");
					Loja l = new Loja();
					Endereco e = new Endereco();					
					Produto p = new Produto();					
					p.setId(j.getLong("idProd"));
					p.setNome(j.getString("nomeProd"));
					p.setDescricao(j.getString("descProd"));
					p.setValor(j.getDouble("prcProd"));
					l.setId(j.getLong("idLoja"));
					l.setNomeFantasia(j.getString("nomeLoja"));
					l.setTelefone(j.getString("foneLoja"));
					e.setLongitude(j.getDouble("logLoja"));
					e.setLatitude(j.getDouble("latLoja"));
					l.setEndereco(e);
					p.setLoja(l);
					pList.add(p);					
				}
				
				if (pList.size() > 0){
					Intent i = new Intent(GeoStoreActivity.this, ListaDeProdutosActivity.class);
					i.putExtra("produtos", pList);										
					startActivity(i);
				}else{
					Toast.makeText(this, "Nenhum produto encontrato com esta descrição...", Toast.LENGTH_SHORT).show();
				}
					
			} catch (JSONException e) {
				Log.e("GeoStoreActivity","JSONException: " + e.getMessage());
			}			
		}else{
			Toast.makeText(this, "O servidor pode estar fora do ar, tente novamente mais tarde...", Toast.LENGTH_SHORT).show();
		}
	}
}