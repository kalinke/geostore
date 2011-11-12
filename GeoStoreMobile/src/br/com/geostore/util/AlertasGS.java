package br.com.geostore.util;

import android.app.AlertDialog;
import android.content.Context;

public class AlertasGS {	
		
	
	public void mostraMsg(String titulo, String texto, Context context){			
		
		AlertDialog.Builder message = new AlertDialog.Builder(context);
		message.setTitle(titulo);
		message.setMessage(texto);
		message.setNeutralButton("OK", null);
		message.show();		
	}	
}
