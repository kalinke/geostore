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
import br.com.geostore.validator.NumeroValidator;

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
		
		NumeroValidator numeroValidator = new NumeroValidator();
		
		if(promocao.getProduto()==null)
			throw new RuntimeException("� necess�rio selecionar o produto!");		

		if(promocao.getStatusPromocao()==null || promocao.getStatusPromocao().getDescricao().isEmpty())
	        throw new RuntimeException("� necess�rio selecionar o status!"); 		
		
		if(promocao.getQde_voucher()==null || promocao.getQde_voucher().isEmpty())
            throw new RuntimeException("� necess�rio informar o n�mero de vouchers!");  
		
		//if(numeroValidator.validarNumero(promocao.getQde_voucher()))
			//throw new RuntimeException("N�mero de vouchers inv�lido!");
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
