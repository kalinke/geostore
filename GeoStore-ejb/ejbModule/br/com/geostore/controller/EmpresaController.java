package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.EmpresaDAO;
import br.com.geostore.dao.StatusEmpresaDAO;
import br.com.geostore.entity.Empresa;
import br.com.geostore.entity.StatusEmpresa;
import br.com.geostore.entity.UnidadeFederacao;
import br.com.geostore.entity.Usuario;
import br.com.geostore.validator.DocumentoValidator;

@Name("empresaController")
@Scope(ScopeType.CONVERSATION)
public class EmpresaController {

	@In(create=true) private EmpresaDAO empresaDAO;
	@In(create=true) private StatusEmpresaDAO statusEmpresaDAO;
	
	@In private FacesMessages facesMessages;
	private Usuario usuarioLogado;
	
	private UnidadeFederacao unidadeFederacao = new UnidadeFederacao();	
	private Empresa empresa = new Empresa();
	private Long idEmpresa;
	
	public EmpresaController() {
		this.usuarioLogado = (Usuario) Contexts.getSessionContext().get("usuarioLogado");
	}

	public String nova(){
		this.empresa = new Empresa();
		this.unidadeFederacao = new UnidadeFederacao();	
		
		return "ADICIONAR";
	}
	

	public String editar() throws Exception{
		this.empresa = empresaDAO.buscarPorId(idEmpresa);
		this.unidadeFederacao = this.empresa.getEndereco().getCidade().getUnidadeFederacao();
		
		return "EDITAR";
	}


	public String cancelar(){	
		return "CANCELAR";
	}
	
	
	public String salvar(){		
		try{			
			validar();			
			
			empresaDAO.salvar(empresa);		
			return "SALVAR";
			
		}catch (Exception e) {
			facesMessages.add(e.getMessage());
			return null;
		}		
	}
	
	public void validar() throws Exception{		
		
		if(!DocumentoValidator.validarCNPJCPF(empresa.getDocumento()))			
			throw new RuntimeException("CNPJ digitado é inválido!");			
				
		if (empresaDAO.buscarPorCNPJ(empresa))				
			throw new RuntimeException("CNPJ digitado já existe!");			

			
		if(empresa.getEndereco().getCidade()==null || empresa.getEndereco().getCidade().getDescricao().isEmpty())
			throw new RuntimeException("Selecione uma Cidade!");
					
	}	
	
	public String remover() throws Exception{		
		empresaDAO.excluir(empresa);
		return "EXCLUIR";
	}

	@Factory
	public List<Empresa> getEmpresas() throws Exception{		
		return empresaDAO.buscarPorUsuarioLogado(usuarioLogado);
	}
	
	@Factory
	public List<StatusEmpresa> getStatusEmpresas() throws Exception{		
		return statusEmpresaDAO.buscarTodos();
	}
	
	
	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}
	
	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
