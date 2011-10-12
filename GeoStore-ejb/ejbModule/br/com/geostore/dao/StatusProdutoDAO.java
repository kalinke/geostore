package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.StatusProduto;

@Name("statusProdutoDAO")
public class StatusProdutoDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;

		
	@SuppressWarnings("unchecked")
	public List<StatusProduto> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de StatusProduto do Banco de Dados");
			Query query = entityManager.createQuery("from StatusProduto as s " +
													"order by s.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	
}
