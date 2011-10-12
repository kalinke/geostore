package br.com.geostore.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.com.geostore.entity.Empresa;
import br.com.geostore.entity.TipoUsuario;
import br.com.geostore.entity.Usuario;

@Name("authenticator")
public class Authenticator
{
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    @In private FacesMessages facesMessages;
    private Usuario usuario;
    
    
    public boolean authenticate(){
    	try{
    		
            log.info("authenticating {0}", credentials.getUsername());

            TipoUsuario tipoUsuario = new TipoUsuario();
            
            if (credentials.getUsername().equals("admin")){  
            	usuario = new Usuario();
            	tipoUsuario.setId(1l);
            	
            	identity.addRole("admin"); 
            	usuario.setNome("Admin");
            	usuario.setEmail("admin@geostore.com");
            	usuario.setId(1l);    
            	usuario.setTipoUsuario(tipoUsuario);
            	
            	Contexts.getSessionContext().set("usuarioLogado", usuario);
            	
            	return true;
            	
            }else if(credentials.getUsername().equals("operador")){
            	usuario = new Usuario();
            	identity.addRole("loja");
            	usuario.setNome("Operador de Loja");
            	usuario.setEmail("operador@loja.com");
            	usuario.setId(2l);        	
            	
            	tipoUsuario.setId(2l);
            	Empresa empresa = new Empresa();
            	empresa.setId(1l);        	
            	usuario.setEmpresaVinculo(empresa);
            	usuario.setTipoUsuario(tipoUsuario);
            	
            	Contexts.getSessionContext().set("usuarioLogado", usuario);		
            	
            	return true;
            }
    		
    	}catch (Exception e) {
			facesMessages.add(e.getMessage());
		}
                
        return false;
    }
    
    public Usuario getUsuario() {
		return usuario;
	}
    
    public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
