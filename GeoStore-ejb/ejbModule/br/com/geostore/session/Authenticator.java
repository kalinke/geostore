package br.com.geostore.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.com.geostore.dao.UsuarioDAO;
import br.com.geostore.entity.Usuario;

@Name("authenticator")
public class Authenticator{
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    @In private FacesMessages facesMessages;
    @In(create=true) UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private Usuario usuarioLogado;
    
    
    public boolean authenticate(){
    	try{
    		
    		usuario = new Usuario();
            log.info("authenticating {0}", credentials.getUsername());

            usuario.setEmail(credentials.getUsername());
            usuario.setSenha(credentials.getPassword());
            	
            usuarioLogado = usuarioDAO.buscarAutenticacao(usuario);            	            	
            	
            if(usuarioLogado == null)            	
            	throw new RuntimeException("Erro na Autenticação! Verifique usuário e senha!");
            
            if(usuarioLogado.getStatusUsuario().getId().longValue() != 1)            	
            	throw new RuntimeException("Usuário inativo ou bloqueado!");

            if(usuarioLogado.getTipoUsuario().getId().longValue() == 3)            	
            	throw new RuntimeException("Usuário apenas Mobile");
            
            if(usuarioLogado.getTipoUsuario().getId().longValue() == 1)
            	identity.addRole("admin");
            else if(usuarioLogado.getTipoUsuario().getId().longValue() == 2)
            	identity.addRole("operador");
            else
            	throw new RuntimeException("Perfil de acesso incorreto!");
            
            
            Contexts.getSessionContext().set("usuarioLogado", usuarioLogado);
            return true;

    		
    	}catch (Exception e) {
			facesMessages.add(e.getMessage());
			return false;
		}
    }
    
    public Usuario getUsuario() {
		return usuario;
	}
    
    public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
