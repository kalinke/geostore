package br.com.geostore.email.controller;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Renderer;

import br.com.geostore.entity.Usuario;


@Name("faleConoscoController")
public class FaleConoscoController {

	@In(create=true) private Renderer renderer;
	@In(create=true) private FacesMessages facesMessages;
	private String email;	
	private String assunto;
	private String mensagem;	
	private Usuario usuarioLogado;
	
	@In(create=true) private Usuario usuario = new Usuario();
	   
	public FaleConoscoController() {
		this.usuarioLogado = (Usuario) Contexts.getSessionContext().get("usuarioLogado");
	}
	
	public void send() {
	    try {
	    	if(this.usuarioLogado!=null){
	    		this.usuario = this.usuarioLogado;
	    	}	
	    	
	    	renderer.render("/EmailLayout/FaleConoscoLayout.xhtml");
	    	facesMessages.add("Mensagem enviada com sucesso!");
	       
	   }catch(RuntimeException e){
		   facesMessages.add(e.getMessage());		   
	   }catch (Exception e) {
	       facesMessages.add("Erro ao enviar: " + e.getMessage());
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

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
}
