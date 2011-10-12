package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.geostore.dao.ProdutoDAO;
import br.com.geostore.entity.Produto;

@Name("produtoController")
@Scope(ScopeType.CONVERSATION)
public class ProdutoController {

	@In(create=true) private ProdutoDAO produtoDAO;

//	@In private FacesMessages facesMessages;
	
	private Produto produto = new Produto();
	private Long idProduto;
		
			
	public String novo(){
		this.produto = new Produto();
		
		return "ADICIONAR";
	}
	

	public String editar() throws Exception{
		this.produto = produtoDAO.buscarPorId(idProduto);
		return "EDITAR";
	}


	public String cancelar(){		
		return "CANCELAR";
	}
	
	
	public String salvar() throws Exception{
		
		produtoDAO.salvar(produto);		
		return "SALVAR";
	}



	public String remover() throws Exception{		
		
		produtoDAO.excluir(produto);
		return "EXCLUIR";
	}
		
	@Factory
	public List<Produto> getProdutos() throws Exception{		
		return produtoDAO.buscarTodos();
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Long getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
}
