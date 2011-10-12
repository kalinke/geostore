package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.geostore.dao.UsuarioDAO;
import br.com.geostore.entity.Usuario;

@Name("usuarioController")
@Scope(ScopeType.CONVERSATION)
public class UsuarioController {

	@In(create=true) private UsuarioDAO usuarioDAO;

//	@In private FacesMessages facesMessages;
	
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
		
		usuarioDAO.salvar(usuario);		
		return "SALVAR";
	}



	public String remover() throws Exception{		
		
		usuarioDAO.excluir(usuario);
		return "EXCLUIR";
	}
		
	@Factory
	public List<Usuario> getUsuarios() throws Exception{		
		return usuarioDAO.buscarTodos();
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
