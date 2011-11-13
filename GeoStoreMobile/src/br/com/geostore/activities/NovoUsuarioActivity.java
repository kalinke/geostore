package br.com.geostore.activities;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.geostore.entity.Usuario;
import br.com.geostore.http.HttpGS;
import br.com.geostore.validator.DocumentoValidator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovoUsuarioActivity extends Activity {
	
	private static final int INCLUIU    = 0; 
	private static final int CPFEXIST   = 1; 
	private static final int EMAILEXIST = 2;
	private static final int DADOSINV   = 3;
	private static final int CPFINV     = 4;
	private static final int ERRO       = 9;
	private static final int OK         = 99;
	
	private Usuario usuario;
	private int retorno;
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.cadastro_tela);
		
		Button btRegistrar = (Button)findViewById(R.id.btRegCad);			
		
		btRegistrar.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				retorno = NovoUsuarioActivity.OK;
				
				EditText edtNome  = (EditText)findViewById(R.id.etNomeCad);
				EditText edtCPF   = (EditText)findViewById(R.id.etCPFCad);
				EditText edtEmail = (EditText)findViewById(R.id.etEmailCad);
				EditText edtSenha = (EditText)findViewById(R.id.etSenhaCad);
				
				usuario = new Usuario();
				usuario.setNome(edtNome.getText().toString());
				usuario.setCpf(edtCPF.getText().toString());
				usuario.setEmail(edtEmail.getText().toString());
				usuario.setSenha(edtSenha.getText().toString());								
				
				if (validaDados()){
					novoUsuario();
				}
				
				switch (retorno){
					case INCLUIU:
						Toast.makeText(NovoUsuarioActivity.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
						finish();
						break;
					case CPFEXIST:
						Toast.makeText(NovoUsuarioActivity.this, "Seu CPF já existe em nossa base de dados!", Toast.LENGTH_SHORT).show();
						break;
					case EMAILEXIST:
						Toast.makeText(NovoUsuarioActivity.this, "Seu e-mail já existe em nossa base de dados!", Toast.LENGTH_SHORT).show();
						break;									
					case DADOSINV:
						Toast.makeText(NovoUsuarioActivity.this, "Dados inválidos para criação do usuário...", Toast.LENGTH_SHORT).show();
						break;
					case CPFINV:
						Toast.makeText(NovoUsuarioActivity.this, "CPF inválido.", Toast.LENGTH_SHORT).show();
						break;
					case ERRO:
						Toast.makeText(NovoUsuarioActivity.this, "Ocorreu um erro, por favor, tente mais tarde!", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		});
	}
	
	public boolean validaDados(){
		
		if (usuario.getNome().equals("")){
			this.retorno = NovoUsuarioActivity.DADOSINV;
		}else if (usuario.getCpf().equals("")){
			this.retorno = NovoUsuarioActivity.DADOSINV;
		}else if (!DocumentoValidator.validarCNPJCPF(usuario.getCpf())){
			this.retorno = NovoUsuarioActivity.CPFINV;
		}else if (usuario.getEmail().equals("")){
			this.retorno = NovoUsuarioActivity.DADOSINV;
		}else if (usuario.getSenha().equals("")){
			this.retorno = NovoUsuarioActivity.DADOSINV;
		}
		
		return this.retorno==NovoUsuarioActivity.OK;

	}
	
	public void novoUsuario(){
				
		HttpGS http = new HttpGS(this);
		String response = http.novoUsuario(this.usuario);
		if (response!=null){
			try {
				
				JSONObject jObj = new JSONObject(response);
				this.retorno = jObj.getInt("retorno");				
				
			} catch (JSONException e) {
				
				this.retorno = this.ERRO;
				e.printStackTrace();
				
			}
		}
	}
}