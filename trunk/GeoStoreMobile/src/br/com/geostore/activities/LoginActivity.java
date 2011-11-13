package br.com.geostore.activities;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.geostore.entity.Usuario;
import br.com.geostore.http.HttpGS;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.login_tela);
		
		Button btLogin = (Button)findViewById(R.id.btLoginLog);
		TextView tvCadastro = (TextView)findViewById(R.id.tvCadLoginClick);
		TextView tvRecSenha = (TextView)findViewById(R.id.tvRecSenhaLoginClick);
				
		btLogin.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {										
				
				EditText email = (EditText)findViewById(R.id.etEmailLog);
				EditText senha = (EditText)findViewById(R.id.etSenhaLogin);
				Object login = efetuarLogin(email.getText().toString(),senha.getText().toString());
				
				if (login!=null){
										
					if ((Boolean) login){
						
						Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();					
						finish();
						
					}else{
						
						Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
						
					}
				
				}
			}
		}); 

		tvCadastro.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(LoginActivity.this, NovoUsuarioActivity.class);
                startActivity(i);
			}
		});
		
		tvRecSenha.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(LoginActivity.this, RecuperarSenhaActivity.class);
                startActivity(i);				
			}
		});
	}
	
	public Object efetuarLogin(String email, String senha){
		HttpGS http = new HttpGS(this);
		String response = http.efetuarLogin(email, senha);
		
		if (response!=null){
			try {
				
				JSONObject jObj = new JSONObject(response);
				boolean login = jObj.getBoolean("encontrou");
				
				if (login){
					
					Usuario usuario = new Usuario();			
					usuario.setId(jObj.getLong("id"));
					usuario.setNome(jObj.getString("nome"));
					usuario.setCpf(jObj.getString("cpf"));
					usuario.setEmail(jObj.getString("email"));						
					usuario.setSenha(jObj.getString("senha"));
					
					BuscarActivity.setUsuario(usuario);
					
					return login;
					
				}else{
					
					return login;
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
