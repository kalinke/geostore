package br.com.geostore.email.controller;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Renderer;

import br.com.geostore.dao.UsuarioDAO;
import br.com.geostore.entity.Usuario;


@Name("reenvioSenhaController")
public class ReenvioSenhaController  {

	@In(create=true) private Renderer renderer;
	@In(create=true) private FacesMessages facesMessages;
	@In(create=true) private UsuarioDAO usuarioDAO;
	private String email;	
	private Usuario usuario = new Usuario();
	   
	public String send() {
	    try {
	    	
	    	if(usuarioDAO.buscarReenvio(email).size() < 1)
	    		throw new RuntimeException("E-Mail não encontrado!");
	    	
	    	
	    	usuario = usuarioDAO.buscarReenvio(email).get(0);
	    		    	
	       renderer.render("/EmailLayout/ReenvioSenhaLayout.xhtml");
	       facesMessages.add("Senha reenviada com sucesso!");
	       
	       return "ENVIADO";
	       
	   }catch(RuntimeException e){
		   facesMessages.add(e.getMessage());
		   return null;
	   }catch (Exception e) {
	       facesMessages.add("Erro ao enviar: " + e.getMessage());
	       return null;
	   }
	 
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
