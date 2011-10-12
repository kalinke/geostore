package br.com.geostore.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@Name("telefoneConverter")
@BypassInterceptors
@Converter
public class TelefoneConverter implements javax.faces.convert.Converter{
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
         String telefone = value;
         if (value!= null && !value.equals(""))
        	 telefone = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", "").replace('(', ' ').replace(')', ' ').replace(" ", "");
         return telefone;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
         String telefone = value.toString();
         if (telefone != null && telefone.length() == 10)
        	 telefone = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 6) + "-" + telefone.substring(6, 10 );
         return telefone;
    }
}
