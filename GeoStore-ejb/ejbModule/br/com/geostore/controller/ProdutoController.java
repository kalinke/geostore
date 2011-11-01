package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.ProdutoDAO;
import br.com.geostore.dao.StatusProdutoDAO;
import br.com.geostore.entity.Produto;
import br.com.geostore.entity.StatusProduto;
import br.com.geostore.entity.Usuario;

@Name("produtoController")
@Scope(ScopeType.CONVERSATION)
public class ProdutoController {

	@In(create=true) private ProdutoDAO produtoDAO;
	@In(create=true) private StatusProdutoDAO statusProdutoDAO;
	
	@In private FacesMessages facesMessages;
	private Usuario usuarioLogado;
	
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
		try{
			
			validar();
			produtoDAO.salvar(produto);	
			return "SALVAR";	
			
		}catch(Exception e){
			
			facesMessages.add(e.getMessage());
			return null;
		}
		
	}
	
	public void validar() throws RuntimeException{

		
		if(produto.getLoja()==null || produto.getLoja().getDocumento().isEmpty())
			throw new RuntimeException("É necessário selecionar a loja!");
		
		if(produto.getStatusProduto()==null || produto.getStatusProduto().getDescricao().isEmpty())
	        throw new RuntimeException("É necessário selecionar o status!"); 
		 
		if(produto.getNome().isEmpty())
            throw new RuntimeException("É necessário preencher o nome!");              
        
        if(produto.getValor()==null)
            throw new RuntimeException("É necessário preencher o valor!");       
       
	}	


	public String remover() throws Exception{		
		
		produtoDAO.excluir(produto);
		return "EXCLUIR";
	}
		
	@Factory
	public List<Produto> getProdutos() throws Exception{		
		return produtoDAO.buscarTodos(usuarioLogado);
	}

	@Factory
	public List<StatusProduto> getStatusProdutos() throws Exception{		
		return statusProdutoDAO.buscarTodos();
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
