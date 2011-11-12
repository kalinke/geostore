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
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.login_tela);
		
		Button btLogin = (Button)findViewById(R.id.btLoginLog);
		TextView tvCadastro = (Button)findViewById(R.id.tvCadLoginClick);
		TextView tvRecSenha = (Button)findViewById(R.id.tvRecSenhaLoginClick);
				
		btLogin.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {										
				
				EditText email = (EditText)findViewById(R.id.etEmailLog);
				EditText senha = (EditText)findViewById(R.id.etSenhaLogin);
				Usuario usuario = efetuarLogin(email.getText().toString(),senha.getText().toString());
				
				if (usuario != null){
					Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
					
					/*Bundle params = new Bundle();
					params.putBoolean("logado", true);*/
					BuscarActivity.setIdUsuario(usuario.getId());
					Intent it = new Intent(LoginActivity.this, BuscarActivity.class);
					//it.putExtras(params);
					startActivity(it);
					
				}else{
					Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
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
