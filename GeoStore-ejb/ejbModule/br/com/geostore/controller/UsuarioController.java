package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.StatusUsuarioDAO;
import br.com.geostore.dao.UsuarioDAO;
import br.com.geostore.entity.StatusUsuario;
import br.com.geostore.entity.Usuario;
import br.com.geostore.validator.DocumentoValidator;
import br.com.geostore.validator.EmailValidator;

@Name("usuarioController")
@Scope(ScopeType.CONVERSATION)
public class UsuarioController {

	@In(create=true) private UsuarioDAO usuarioDAO;
	@In(create=true) private StatusUsuarioDAO statusUsuarioDAO;

	@In private FacesMessages facesMessages;
	private Usuario usuarioLogado;
	
	private Usuario usuario = new Usuario();
	private Long idUsuario;
	
	private String senhaAtual;
	private String novaSenha;
	private String confirmaNovaSenha;
	private String acao="NOVA";
			
	public UsuarioController() {
		this.usuarioLogado = (Usuario) Contexts.getSessionContext().get("usuarioLogado");
	}
	
	public String novo(){
		this.usuario = new Usuario();
		acao = "NOVA";
		return "ADICIONAR";
	}
	

	public String editar() throws Exception{
		this.usuario = usuarioDAO.buscarPorId(idUsuario);
		acao = "EDITAR";
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
	
	public String alterarSenha() throws Exception{
		try{	
			
			if(!senhaAtual.equals(usuarioLogado.getSenha()))
				throw new RuntimeException("A senha atual está incorreta!");
			
			if(novaSenha.isEmpty() || confirmaNovaSenha.isEmpty())
				throw new RuntimeException("É necessário preencher todos os campos!");
			
	        if(novaSenha.length() < 6)
	        	throw new RuntimeException("A senha deve conter no mínimo 6 caracteres!");
			
			if(!novaSenha.equals(confirmaNovaSenha))
				throw new RuntimeException("Os campos 'Nova senha' e 'Confirmar nova senha' devem ser iguais!");
			
			usuario = usuarioLogado;
			usuario.setSenha(novaSenha);
				
			usuarioDAO.alterar(usuario);
			Contexts.getSessionContext().set("usuarioLogado", usuario);

			return "SALVAR";

		}catch (Exception e) {
			facesMessages.add(e.getMessage());
			return null;
		}
		
	}
	
	public void validar() throws Exception{
		
		EmailValidator emailValidator = new EmailValidator();		
		//NomeValidator nomeValidator = new NomeValidator();
		
		if(usuario.getTipoUsuario()==null || usuario.getTipoUsuario().getDescricao().isEmpty())
			throw new RuntimeException("É necessário selecionar o tipo de usuário!");
		
		if(usuario.getTipoUsuario().getId()==2 && usuario.getEmpresaVinculo()==null)
			throw new RuntimeException("É necessário selecionar a empresa!");
		
		if(usuario.getStatusUsuario()==null || usuario.getStatusUsuario().getDescricao().isEmpty())
	        throw new RuntimeException("É necessário selecionar o status!"); 
		 
		if(usuario.getNome().isEmpty())
            throw new RuntimeException("É necessário preencher o nome!");       

		//if(!nomeValidator.validarNome(usuario.getNome()))
           // new RuntimeException("Nome inválido!");          

		if(usuario.getCpf().isEmpty())
			throw new RuntimeException("Digite um CPF!");        
		
		if(!DocumentoValidator.validarCNPJCPF(usuario.getCpf()))
			throw new RuntimeException("CPF Inválido!");       
		
		if(usuarioDAO.buscarPorCPF(usuario, acao))
			throw new RuntimeException("CPF já existe!");       
		
		if(usuario.getEmail().isEmpty())
            throw new RuntimeException("É necessário preencher o email!");       

		if(usuarioDAO.buscarPorEmail(usuario, acao))
			throw new RuntimeException("E-Mail já existe!");
		
        if(!emailValidator.validarEmail(usuario.getEmail()))
            throw new RuntimeException("Email inválido!");        
        
        
        if(usuario.getSenha().isEmpty())
            throw new RuntimeException("É necessário preencher a senha!");         
        
        if(usuario.getSenha().length() < 6)
        	throw new RuntimeException("A senha deve conter no mínimo 6 caracteres!");
        
	}	


	public String remover() throws Exception{		
		if(usuario.getId()==null)
			facesMessages.add("Não existem dados para excluir, voltando a tela de controle.");
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

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaNovaSenha() {
		return confirmaNovaSenha;
	}

	public void setConfirmaNovaSenha(String confirmaNovaSenha) {
		this.confirmaNovaSenha = confirmaNovaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}	
	
}
