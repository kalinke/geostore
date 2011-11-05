package br.com.geostore.activities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.ajax4jsf.javascript.JSObject;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity{
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.login_tela);
		
		Button btLogin = (Button)findViewById(R.id.btLoginLog);
		Button btCadastro = (Button)findViewById(R.id.btCadastrarLog);
		//Button btSair = (Button)findViewById(R.id.btSairLog);
						
		btLogin.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {				
				//String emailLog,senhaLog;				
				EditText emailLog = (EditText)findViewById(R.id.etEmailLog);
				EditText senhaLog = (EditText)findViewById(R.id.etSenhaLogin);				
				String url = "http://10.0.2.2:8080/GeoStore/LoginServlet?login="+emailLog.getText().toString()+"&senha="+senhaLog.getText().toString();								
				try {
					//URL u = new URL(url);
					GetHttp gh = new GetHttp(url);
					String st = gh.page;					
					JSONObject js = (JSONObject)new JSONTokener(st).nextValue();
					Log.d("retorno" , js.getString("Logado"));
					//HttpURLConnection conn = (HttpURLConnection) u.openConnection();					
					/*
					Map params = new HashMap();
					params.put("login", emailLog.getText().toString());
					params.put("senha", senhaLog.getText().toString());
					*/					
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
				//mostraMsg("Dados Login","Login: " + emailLog + " Senha: " + senhaLog);				
			}
		}); 
		//chama o cadastro
		btCadastro.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				final Intent i = new Intent(Login.this, Cadastro.class);
                startActivity(i);
			}
		});
		/*sai da aplicação
		btSair.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});*/
	}	
}
