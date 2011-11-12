package br.com.geostore.activities;

import br.com.geostore.entity.Usuario;
import br.com.geostore.http.HttpGS;
import br.com.geostore.json.JsonGS;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.login_tela);
		
		Button btLogin = (Button)findViewById(R.id.btLoginLog);
		Button btCadastro = (Button)findViewById(R.id.btCadastrarLog);
		
				
		btLogin.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {										
				
				EditText email = (EditText)findViewById(R.id.etEmailLog);
				EditText senha = (EditText)findViewById(R.id.etSenhaLogin);
				Usuario usuario = efetuarLogin(email.getText().toString(),senha.getText().toString());
				
				if (usuario != null){
					Toast.makeText(Login.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
					
					/*Bundle params = new Bundle();
					params.putBoolean("logado", true);*/
					GeoStoreActivity.setIdUsuario(usuario.getId());
					Intent it = new Intent(Login.this, GeoStoreActivity.class);
					//it.putExtras(params);
					startActivity(it);
					
				}else{
					Toast.makeText(Login.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
				}
			}
		}); 

		btCadastro.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Login.this, NovoUsuario.class);
                startActivity(i);
			}
		});
	}
	
	public Usuario efetuarLogin(String email, String senha){
		HttpGS http = new HttpGS(this);
		String response = http.efetuarLogin(email, senha);
		JsonGS json = new JsonGS();
		Usuario usuario = json.JSonObjectToUsuario(response);
		return usuario;
	}
}
