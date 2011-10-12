package br.com.geostore.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@Name("cepConverter")
@BypassInterceptors
@Converter
public class CEPConverter implements javax.faces.convert.Converter{
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        String cep = value;
        if (value!= null && !value.equals(""))
        	cep = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", "");
        return cep;
   }

   public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        String cep = value.toString();
        if (cep != null && cep.length() == 8)
        	cep = cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5, 8);
        return cep;
   }
	

}
