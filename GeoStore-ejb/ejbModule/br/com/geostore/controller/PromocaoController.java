package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.PromocaoDAO;
import br.com.geostore.dao.StatusPromocaoDAO;
import br.com.geostore.entity.Produto;
import br.com.geostore.entity.Promocao;
import br.com.geostore.entity.StatusPromocao;

@Name("promocaoController")
@Scope(ScopeType.CONVERSATION)
public class PromocaoController {
	
	@In(create=true) private PromocaoDAO promocaoDAO;
	@In(create=true) private StatusPromocaoDAO statusPromocaoDAO;
	
	@In private FacesMessages facesMessages;
	
	private Promocao promocao = new Promocao();
	private Long idPromocao;
	private Produto produto;

	public String novo(){
		this.promocao = new Promocao();	
		this.produto = new Produto();
		return "ADICIONAR";
	}
	
	public String editar() throws Exception{
		this.promocao = promocaoDAO.buscarPorId(idPromocao);
		return "EDITAR";
	}


	public String cancelar(){		
		return "CANCELAR";
	}
	
	public String salvar() throws Exception{
		try{
			
			validar();
			promocaoDAO.salvar(promocao);		
			return "SALVAR";	
			
		}catch(Exception e){
			
			facesMessages.add(e.getMessage());
			return null;
		}
		
	}
	
	public void validar() throws RuntimeException{  
       
	}	
	
	public String remover() throws Exception{		
	
		promocaoDAO.excluir(promocao);
		return "EXCLUIR";
	}
	
	@Factory
	public List<Promocao> getPromocoes() throws Exception{		
		return promocaoDAO.buscarTodos();
	}
	
	@Factory
	public List<StatusPromocao> getStatusPromocoes()throws Exception{		
		return statusPromocaoDAO.buscarTodos();
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public Long getIdPromocao() {
		return idPromocao;
	}

	public void setIdPromocao(Long idPromocao) {
		this.idPromocao = idPromocao;
	}
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
