package br.com.geostore.activities;

import java.util.HashMap;
import java.util.Map;

import br.com.geostore.http.HttpGS;
import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class GeoStoreActivity extends Activity implements OnClickListener{    	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_busca_tela);        
        Button btnBuscar = (Button) findViewById(R.id.btBuscarMainBusca);        
        btnBuscar.setOnClickListener(this);
                 
        }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onClick(View v) {			
				
		EditText edtBuscar = (EditText) findViewById(R.id.etItemMainBusca);
		double log = 0;
		double lat = 0;
		double raio = 1000;
		
		Map params = new HashMap();
		params.put("texto", edtBuscar.getText().toString());
		params.put("log", log);
		params.put("lat", lat);
		params.put("raio", raio);
		
		HttpGS h = new HttpGS("produtoServlet");
		h.doPost(params);

		
	}
	
		
}