package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.LojaDAO;
import br.com.geostore.dao.StatusLojaDAO;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.StatusLoja;
import br.com.geostore.entity.UnidadeFederacao;
import br.com.geostore.validator.DocumentoValidator;

@Name("lojaController")
@Scope(ScopeType.CONVERSATION)
public class LojaController {

	@In(create=true) private LojaDAO lojaDAO;
	@In(create=true) private StatusLojaDAO statusLojaDAO;
	
	@In private FacesMessages facesMessages;
	
	private boolean validaDocumento;
	
	private Loja loja = new Loja();
	private UnidadeFederacao unidadeFederacao = new UnidadeFederacao();	
	private Long idLoja;
	
			
	public String nova(){
		this.loja = new Loja();
		this.unidadeFederacao = new UnidadeFederacao();	
		
		return "ADICIONAR";
	}
	

	public String editar() throws Exception{
		this.loja = lojaDAO.buscarPorId(idLoja);
		this.unidadeFederacao = this.loja.getEndereco().getCidade().getUnidadeFederacao();
		return "EDITAR";
	}


	public String cancelar(){		
		return "CANCELAR";
	}
	
	
	public String salvar() throws Exception{
		
		try{			
			validar();
					
			lojaDAO.salvar(loja);		
			return "SALVAR";
			
		}catch (Exception e) {
			facesMessages.add(e.getMessage());
			return null;
		}		

	}
	
	public void validar() throws RuntimeException{
		
		validaDocumento = DocumentoValidator.validarCNPJCPF(loja.getDocumento());
		
		if(validaDocumento == false){
			throw new RuntimeException("CNPJ Digitado é inválido!");
		}
			
		if(loja.getEndereco().getCidade()==null || loja.getEndereco().getCidade().getDescricao().isEmpty()){
			throw new RuntimeException("Selecione uma Cidade!");
		}
		
	}	

	public String remover() throws Exception{		
		
		lojaDAO.excluir(loja);
		return "EXCLUIR";
	}
	
	@Factory
	public List<Loja> getLojas() throws Exception{		
		return lojaDAO.buscarTodos();
	}
	
	@Factory
	public List<StatusLoja> getStatusLojas() throws Exception{		
		return statusLojaDAO.buscarTodos();
	}
	
	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}
	
	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}
	
	public Loja getLoja() {
		return loja;
	}


	public void setLoja(Loja loja) {
		this.loja = loja;
	}


	public Long getIdLoja() {
		return idLoja;
	}


	public void setIdLoja(Long idLoja) {
		this.idLoja = idLoja;
	}
		
	
}
