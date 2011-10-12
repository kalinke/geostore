package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.geostore.dao.StatusUsuarioDAO;
import br.com.geostore.dao.UsuarioDAO;
import br.com.geostore.entity.StatusUsuario;
import br.com.geostore.entity.Usuario;
import br.com.geostore.validator.EmailValidator;
import br.com.geostore.validator.NomeValidator;

@Name("usuarioController")
@Scope(ScopeType.CONVERSATION)
public class UsuarioController {

	@In(create=true) private UsuarioDAO usuarioDAO;
	@In(create=true) private StatusUsuarioDAO statusUsuarioDAO;

//	@In private FacesMessages facesMessages;
	
	EmailValidator emailValidator = new EmailValidator();
	private boolean validaEmail;
	
	NomeValidator nomeValidator = new NomeValidator();
	private boolean validaNome;
	
	private Usuario usuario = new Usuario();
	private Long idUsuario;
			
	public String novo(){
		this.usuario = new Usuario();
		
		return "ADICIONAR";
	}
	

	public String editar() throws Exception{
		this.usuario = usuarioDAO.buscarPorId(idUsuario);
		return "EDITAR";
	}


	public String cancelar(){		
		return "CANCELAR";
	}
	
	
	public String salvar() throws Exception{
		validar();
		
		usuarioDAO.salvar(usuario);		
		return "SALVAR";
	}
	
	public void validar() throws RuntimeException{
		
		validaEmail = emailValidator.validarEmail(usuario.getEmail());
		validaNome = nomeValidator.validarNome(usuario.getNome());
		
		if(validaEmail == false){
			throw new RuntimeException("Email inválido!");
		}
		
		if(validaNome == false){
			throw new RuntimeException("Nome inválido!");
		}

	}	


	public String remover() throws Exception{		
		
		usuarioDAO.excluir(usuario);
		return "EXCLUIR";
	}
		
	@Factory
	public List<Usuario> getUsuarios() throws Exception{		
		return usuarioDAO.buscarTodos();
	}

	@Factory
	public List<StatusUsuario> getStatusUsuarios() throws Exception{		
		return statusUsuarioDAO.buscarTodos();
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
		
	
}
