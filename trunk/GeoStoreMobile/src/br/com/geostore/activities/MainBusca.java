package br.com.geostore.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainBusca extends Activity {
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.main_busca_tela);		
		
		TextView tvLogin = (TextView)findViewById(R.id.tvLoginMainBuscaclick);
		Button btBusca = (Button)findViewById(R.id.btBuscarMainBusca);
				
		tvLogin.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				final Intent i = new Intent(MainBusca.this, Login.class);  //error on this line
                startActivity(i);
			}
		});		
		
		btBusca.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				final Intent i = new Intent(MainBusca.this, ListaProd.class);  //error on this line
                startActivity(i);
			}
		});
		
		Spinner sp = (Spinner)findViewById(R.id.spinner1);
	    ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.raio, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    sp.setAdapter(adapter);		
	}
	
	public void mostraMsg(String titulo, String texto){		
		AlertDialog.Builder message = new AlertDialog.Builder(MainBusca.this);
		message.setTitle(titulo);
		message.setMessage(texto);
		message.setNeutralButton("OK", null);
		message.show();		
	}
}
