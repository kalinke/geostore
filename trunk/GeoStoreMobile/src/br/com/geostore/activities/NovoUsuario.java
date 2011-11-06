package br.com.geostore.activities;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.geostore.entity.Usuario;
import br.com.geostore.http.HttpGS;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovoUsuario extends Activity {
	
	protected static final int INCLUIU    = 0; 
	protected static final int CPFEXIST   = 1; 
	protected static final int EMAILEXIST = 2;
	
	private String nome = null;
	private String cpf  = null;
	private String email= null;
	private String senha= null;
	private String msg  = null;
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.cadastro_tela);
		
		Button btRegistrar = (Button)findViewById(R.id.btRegCad);			
		
		btRegistrar.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				EditText edtNome  = (EditText)findViewById(R.id.etNomeCad);
				EditText edtCPF   = (EditText)findViewById(R.id.etCPFCad);
				EditText edtEmail = (EditText)findViewById(R.id.etEmailCad);
				EditText edtSenha = (EditText)findViewById(R.id.etSenhaCad);
				
				nome  = edtNome.getText().toString();
				cpf   = edtCPF.getText().toString();
				email = edtEmail.getText().toString();
				senha = edtSenha.getText().toString();								
				
				if (validaDados()){
					if (novoUsuario()){
						Toast.makeText(NovoUsuario.this, msg, Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(NovoUsuario.this, "Ocorreu um erro, por favor, tente mais tarde!", Toast.LENGTH_SHORT).show();
					}
				}else{
					Toast.makeText(NovoUsuario.this, "Dados inválidos para criação do usuário...", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	public boolean validaDados(){
		
		boolean dados = true;
		
		if (nome.equals("")){
			dados = false;
		}else if (cpf.equals("")){
			dados = false;
		}else if (email.equals("")){
			dados = false;
		}else if (senha.equals("")){
			dados = false;
		}				
		
		return dados;
	}
	
	public boolean novoUsuario(){
		
		Usuario usuario = new Usuario();		
		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setEmail(email);				
		usuario.setSenha(senha);
		
		HttpGS http = new HttpGS();
		String response = http.novoUsuario(usuario);
		try {
			
			JSONObject jObj = new JSONObject(response);
			int i = jObj.getInt("incluiu");
			
			switch (i){
				case INCLUIU:
					this.msg = "Cadastro efetuado com sucesso!";
					break;
				case CPFEXIST:
					this.msg = "Seu CPF já existe em nossa base de dados!";
					break;
				case EMAILEXIST:
					this.msg = "Seu e-mail já existe em nossa base de dados!";
					break;				
			}		
			
			return true;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}