package br.com.geostore.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.geostore.http.HttpGS;

public class RecuperarSenhaActivity extends Activity{
	
	private static final String TAG = "RecuperarSenhaActivity";
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.recuperarsenha);
		
		Button btEnviar = (Button)findViewById(R.id.btEnviarSenhaRec);
		final EditText etEmail = (EditText)findViewById(R.id.etEmailSenhaRec);
				
		//envia email para recuperar senha
		btEnviar.setOnClickListener(new View.OnClickListener() {	
			
			public void onClick(View v){
				recuperarSenha();
			}

			public void recuperarSenha() {
				String email = etEmail.getText().toString();
				
				HttpGS hgs = new HttpGS(RecuperarSenhaActivity.this);
				
				String response = hgs.recuperaSenha(email);
				String msg = null;
				if (response != null){
					if(response.equals("ENVIADO")){					
						
						msg = "Senha enviada com sucesso.";
						
					}else{
	
						msg = "Erro, verifique o e-mail e tente novamente.";
						
					}
					
					AlertDialog alertDialog = new AlertDialog.Builder(RecuperarSenhaActivity.this).create();  
				    alertDialog.setTitle("Recupera senha");  
				    alertDialog.setMessage(msg);  
				    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {  
				      public void onClick(DialogInterface dialog, int which) {  
				    	  finish();
				    	  return;  
				    } });
				    alertDialog.show();		
				}
			}		
			
		});
	}
}