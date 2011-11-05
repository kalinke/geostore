package br.com.geostore.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@Name("cpfConverter")
@BypassInterceptors
@Converter
public class CPFConverter implements javax.faces.convert.Converter{
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
         String cnpj = value;
         if (value!= null && !value.equals(""))
              cnpj = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", "");
         return cnpj;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
         String cnpj = value.toString();
         if (cnpj != null && cnpj.length() == 14)
             cnpj = cnpj.substring(0, 3) + "." + cnpj.substring(3, 6) + "." + cnpj.substring(6, 9) + "-" + cnpj.substring(9, 11);
         return cnpj;
    }
}
