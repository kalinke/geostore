package br.com.geostore.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class MeusDadosActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meusdados);
        
        TextView txtNome = (TextView) findViewById(R.id.tvNomeUsuarioMeusDados);
    	txtNome.setText(BuscarActivity.getUsuario().getNome());
    	
    	TextView txtEmail = (TextView) findViewById(R.id.tvEmailUsuarioMeusDados);
    	txtEmail.setText(BuscarActivity.getUsuario().getEmail());
    	
    	TextView txtCpf = (TextView) findViewById(R.id.tvCpfUsuarioMeusDados);
    	txtCpf.setText(BuscarActivity.getUsuario().getCpf());   	
        
	}
	
}
