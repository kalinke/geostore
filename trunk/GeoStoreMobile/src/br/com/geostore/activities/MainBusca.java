package br.com.geostore.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainBusca extends Activity {
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.main_busca_tela);
		
		Button btLogin = (Button)findViewById(R.id.btLoginMainBusca);
		Button btBusca = (Button)findViewById(R.id.btBuscarMainBusca);
		
		btLogin.setOnClickListener(new View.OnClickListener() {			
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
				//mostraMsg("Em Desenvolvimento", "Em Desenvolvimento");
			}
		});
		
	}
	
	public void mostraMsg(String titulo, String texto){		
		AlertDialog.Builder message = new AlertDialog.Builder(MainBusca.this);
		message.setTitle(titulo);
		message.setMessage(texto);
		message.setNeutralButton("OK", null);
		message.show();		
	}

}
