package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

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

	@In private FacesMessages facesMessages;
	private Usuario usuarioLogado;
	
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
		try{			
			validar();	
					
			usuarioDAO.salvar(usuario);		
			return "SALVAR";

		}catch (Exception e) {
			facesMessages.add(e.getMessage());
			return null;
		}
		
	}
	
	public void validar() throws RuntimeException{
		
		EmailValidator emailValidator = new EmailValidator();		
		NomeValidator nomeValidator = new NomeValidator();
		
		if(usuario.getTipoUsuario()==null || usuario.getTipoUsuario().getDescricao().isEmpty())
			throw new RuntimeException("É necessário selecionar o tipo de usuário!");
		
		if(usuario.getTipoUsuario().getId()==2 && usuario.getEmpresaVinculo()==null)
			throw new RuntimeException("É necessário selecionar a empresa!");
		
		if(usuario.getStatusUsuario()==null || usuario.getStatusUsuario().getDescricao().isEmpty())
	        throw new RuntimeException("É necessário selecionar o status!"); 
		 
		if(usuario.getNome().isEmpty())
            throw new RuntimeException("É necessário preencher o nome!");       

		if(!nomeValidator.validarNome(usuario.getNome()))
            throw new RuntimeException("Nome inválido!");          

		if(usuario.getEmail().isEmpty())
            throw new RuntimeException("É necessário preencher o email!");       

        if(!emailValidator.validarEmail(usuario.getEmail()))
            throw new RuntimeException("Email inválido!");        
        
        if(usuario.getSenha().isEmpty())
            throw new RuntimeException("É necessário preencher a senha!");         
       
	}	


	public String remover() throws Exception{		
		
		usuarioDAO.excluir(usuario);
		return "EXCLUIR";
	}
		
	@Factory
	public List<Usuario> getUsuarios() throws Exception{		
		return usuarioDAO.buscarTodos(usuarioLogado);
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
