package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.StatusLoja;

@Name("statusLojaDAO")
public class StatusLojaDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;

		
	@SuppressWarnings("unchecked")
	public List<StatusLoja> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de StatusLoja do Banco de Dados");
			Query query = entityManager.createQuery("from StatusLoja as s " +
													"order by s.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	
}
