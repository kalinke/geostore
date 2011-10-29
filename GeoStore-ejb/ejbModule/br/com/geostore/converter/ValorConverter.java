package br.com.geostore.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@Name("valorConverter")
@BypassInterceptors
@Converter
public class ValorConverter implements javax.faces.convert.Converter{
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        String valor = value;
        if (value!= null && !value.equals(""))
        	valor = value.replaceAll("\\,", "\\.").replaceAll("\\-", "").replaceAll("/", "").replace('(', ' ').replace(')', ' ').replace(" ", "");
        return valor;
   }

   public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        String valor = value.toString();
        //if (valor != null && valor.length() == 10)
        	//valor = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 6) + "-" + telefone.substring(6, 10 );
        return valor;
   }

}
