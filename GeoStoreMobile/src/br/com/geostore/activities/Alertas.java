package br.com.geostore.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

public class Alertas {	
		
	
	public void mostraMsg(String titulo, String texto, Context context){			
		
		AlertDialog.Builder message = new AlertDialog.Builder(context);
		message.setTitle(titulo);
		message.setMessage(texto);
		message.setNeutralButton("OK", null);
		message.show();		
	}	
}
