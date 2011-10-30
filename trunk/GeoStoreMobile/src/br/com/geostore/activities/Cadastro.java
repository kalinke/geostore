package br.com.geostore.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.geostore.entity.Usuario;

public class Cadastro extends Activity {
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.cadastro_tela);
		
		final int INSERIR_EDITAR = 1;
		final int BUSCAR = 2;
		
		Button btRegistrar = (Button)findViewById(R.id.btRegCad);
		Button btSair = (Button)findViewById(R.id.btSairCad);
		
		final EditText etNome = (EditText)findViewById(R.id.etNomeCad);
		final EditText etCPF = (EditText)findViewById(R.id.etCPFCad);
		final EditText etEmail = (EditText)findViewById(R.id.etEmailCad);
		final EditText etSenha = (EditText)findViewById(R.id.etSenhaCad);
		
		btRegistrar.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				try{
					Usuario user = new Usuario();
					user.setNome(etNome.getText().toString());
					//user.setCPF(etCPF.getText().toString());
					user.setEmail(etEmail.getText().toString());
					user.setSenha(etSenha.getText().toString());
					
				}catch(Exception erro){
					mostraMsg("Gravação Registro","Falha ao gravar registros" + erro.getMessage());
				}				
			}
		});   	 
    	
		btSair.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_opt1, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.icon:     Toast.makeText(this, "You pressed the icon!", Toast.LENGTH_LONG).show();
	                            break;
	        case R.id.text:     Toast.makeText(this, "You pressed the text!", Toast.LENGTH_LONG).show();
	                            break;
	        case R.id.icontext: Toast.makeText(this, "You pressed the icon and text!", Toast.LENGTH_LONG).show();
	                            break;
	    }
	    return true;
	}
	
	
	public void mostraMsg(String titulo, String texto){		
		AlertDialog.Builder message = new AlertDialog.Builder(Cadastro.this);
		message.setTitle(titulo);
		message.setMessage(texto);
		message.setNeutralButton("OK", null);
		message.show();		
	}
}