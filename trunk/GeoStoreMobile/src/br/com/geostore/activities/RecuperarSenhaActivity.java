package br.com.geostore.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.geostore.http.HttpGS;
import br.com.geostore.util.AlertasGS;

public class RecuperarSenhaActivity extends Activity{
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
				
				HttpGS hgs = new HttpGS(RecuperarSenhaActivity.this);
				
				String response = hgs.recuperaSenha(email);
				
				if(response.equals("ENVIADO")){					
					AlertasGS.showMsgOk("Recupera senha", "Senha enviada com sucesso.", RecuperarSenhaActivity.this);
				}else{
					AlertasGS.showMsgOk("Recupera senha", "Erro, verifique o e-mail e tente novamente.", RecuperarSenhaActivity.this);
				}
					
			}		
			
		});
	}
}