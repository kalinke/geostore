package br.com.geostore.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumeroValidator {
	
	public boolean validarNumeroLogradouro(String numero){
	
			 Pattern p = Pattern.compile("^[0-9]{1,30}$"); 
			 Matcher m = p.matcher(numero);
			 
			 if (m.matches()==true){			 
				 return true;  
			 }else{	    	  
			     return false; 
			 }

	}
	
	public boolean validarNumero(String numero){

		 Pattern p = Pattern.compile("\\d{3,9}"); 
		 Matcher m = p.matcher(numero);
		 
		 if (m.matches()==true){			 
			 return true;  
		 }else{	    	  
		     return false; 
		 }

}
	
	public boolean validarCoordenadas(double numero){
		 
		 String coordenada = String.valueOf(numero);
		 Pattern p = Pattern.compile("[0-9\\.\\-]{1,40}"); 
		 Matcher m = p.matcher(coordenada);
		 
		 if (m.matches()==true){			 
			 return true;  
		 }else{	    	  
		     return false; 
		 }

}
	

}
