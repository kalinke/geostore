package br.com.geostore.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NomeValidator {
	
	public boolean validarNome(String nome){
		
		 Pattern p = Pattern.compile("^[aA-zZzZãÃõÕêÊâÂôÔóÓúÚáÁéÉíÍàÀ\\s]+((\\s[aA-zZãÃõÕêÊâÂôÔóÓúÚáÁéÉíÍàÀ\\s]+)+)?$"); 
		 Matcher m = p.matcher(nome);
		 
		 if (m.matches()==true){			 
			 return true;  
		 }else{	    	  
		     return false; 
		 }		
		
	}

}
