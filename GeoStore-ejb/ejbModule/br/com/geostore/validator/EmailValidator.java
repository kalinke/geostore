package br.com.geostore.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	public boolean validarEmail(String email){

		 Pattern p = Pattern.compile("^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$"); 
		 Matcher m = p.matcher(email);
		 
		 if (m.matches()==true){			 
			 return true;  
		 }else{	    	  
		     return false; 
		 }
	}
}
