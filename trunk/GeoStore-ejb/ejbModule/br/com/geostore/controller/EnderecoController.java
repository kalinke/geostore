package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.geostore.dao.CidadeDAO;
import br.com.geostore.dao.UnidadeFederacaoDAO;
import br.com.geostore.entity.Cidade;
import br.com.geostore.entity.UnidadeFederacao;

@Name("enderecoController")
public class EnderecoController {
	
	@In(create=true) private UnidadeFederacaoDAO unidadeFederacaoDAO;
	@In(create=true) private CidadeDAO cidadeDAO;
	
	UnidadeFederacao uf = new UnidadeFederacao();
	
	@Factory(value="unidadesFederacao", scope=ScopeType.APPLICATION)
	public List<UnidadeFederacao> populaUnidadesFederacao() throws Exception{		
		return unidadeFederacaoDAO.buscarTodos();
	}
	
	@Factory
	public List<Cidade> getCidades(UnidadeFederacao unidadeFederacao) throws Exception{		
		if(unidadeFederacao!=null)		
			return cidadeDAO.buscarPorUnidadeFederacao(unidadeFederacao);
		else
			return null;
	}
	
	public UnidadeFederacao getUf() {
		return uf;
	}
	
	public void setUf(UnidadeFederacao uf) {
		this.uf = uf;
	}
	
}
