package br.com.geostore.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.geostore.entity.Endereco;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.Produto;
import br.com.geostore.gps.GpsGS;
import br.com.geostore.http.HttpGS;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GeoStoreActivity extends Activity implements Button.OnClickListener, Spinner.OnItemSelectedListener{    	
		
	protected static final int RAIO_ILIMITADO = 0;
	protected static final int RAIO_100       = 1; 
    protected static final int RAIO_500       = 2;
    protected static final int RAIO_1000      = 3;
    protected static final int RAIO_10000     = 4;
    protected static final String TAG = "HttpGS";
    	
    private int raio;
    private boolean logado;
    
    @Override
	@SuppressWarnings("rawtypes")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_busca_tela);        
        
        Intent it = getIntent();
        if (it != null){
        	Bundle params =  it.getExtras();
        	if (params != null){
        		logado = params.getBoolean("logado");
        	}
        }
        
        Button btnBuscar = (Button) findViewById(R.id.btBuscarMainBusca);        
        btnBuscar.setOnClickListener(this);               
        
        Spinner sp = (Spinner)findViewById(R.id.spRaio);			    
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.raio, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    sp.setAdapter(adapter);
	    sp.setOnItemSelectedListener(this);
	    
	    TextView txtLogin = (TextView) findViewById(R.id.tvLoginMainBuscaclick);
	    if(logado){
	    	TextView textViewToChange = (TextView) findViewById(R.id.tvLoginMainBuscaclick);
	    	textViewToChange.setText("Logout");
	    }
	    
        txtLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(GeoStoreActivity.this, Login.class);
	            startActivity(i);
			}
		});	   
	}

	@Override
	public void onClick(View v) {			
				
		EditText edtBuscar = (EditText) findViewById(R.id.etItemMainBusca);
		String log  = "0";
		String lat  = "0";
		
		LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		GpsGS g = new GpsGS(lm);
		lat = Double.toString(g.getLastLatitude());
		log = Double.toString(g.getLastLongitude());		
		
		HttpGS h = new HttpGS(this);
		String s = h.buscarProdutos(edtBuscar.getText().toString(), log, lat, String.valueOf(raio));
			
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
					Toast.makeText(this, "Nenhum produto encontrato...", Toast.LENGTH_LONG).show();
				}
					
			} catch (JSONException e) {
				
				Toast.makeText(this, "O servidor retornou dados inesperados.", Toast.LENGTH_LONG).show();
				Log.e(TAG,"JSONException: " + e.getMessage());
			}								
		}
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		 
		switch(pos){		 
			 case RAIO_ILIMITADO:{
				 this.raio = 0;
				 break;
			 }
			 case RAIO_100:{	    			 
				 this.raio = 100;
				 break;
			 }
			 case RAIO_500:{    			 
				 this.raio = 500;
				 break;
			 }
			 case RAIO_1000:{	    			 
				 this.raio = 1000;
				 break;
			 }
			 case RAIO_10000:{	    			 
				 this.raio = 10000;
				 break;
			 }
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_tela_busca, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.icontext: Toast.makeText(this, "Finalizando GeoStore", Toast.LENGTH_LONG).show();
	        	finish();
	        	break;
	    }
	    return true;
	}
		
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
		
	}
}