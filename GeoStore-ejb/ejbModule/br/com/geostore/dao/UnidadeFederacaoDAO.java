package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.UnidadeFederacao;

@Name("unidadeFederacaoDAO")
public class UnidadeFederacaoDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;
	
	public UnidadeFederacao buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(UnidadeFederacao.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<UnidadeFederacao> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de UnidadeFederacao do Banco de Dados");
			Query query = entityManager.createQuery("from UnidadeFederacao as u " +
													"order by u.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	
}
