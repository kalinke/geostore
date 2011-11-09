package br.com.geostore.activities;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONObject;
import org.json.JSONTokener;

import br.com.geostore.http.HttpGS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.geostore.activities.Alertas;

public class SenhaRecupera extends Activity{
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.senha_recup);
		
		Button btEnviar = (Button)findViewById(R.id.btEnviarSenhaRec);
		final EditText etEmail = (EditText)findViewById(R.id.etEmailSenhaRec);
				
		//envia email para recuperar senha
		btEnviar.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				String email = etEmail.getText().toString();
				
				HttpGS hgs = new HttpGS(SenhaRecupera.this);
				
				String response = hgs.recuperaSenha(email);
				
								
				Alertas msg = new Alertas();
				
				if(response.equals("ENVIADO")){					
					msg.mostraMsg("Recupera senha", "Senha enviada com sucesso.", SenhaRecupera.this);
				}else{
					msg.mostraMsg("Recupera senha", "Erro, verifique o e-mail e tente novamente.", SenhaRecupera.this);
				}
					
			}		
			
		});
	}
}