package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Cidade;
import br.com.geostore.entity.UnidadeFederacao;

@Name("cidadeDAO")
public class CidadeDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;
	
	public Cidade buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(Cidade.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorUnidadeFederacao(UnidadeFederacao unidadeFederacao) throws Exception {
		try{
			Query query = entityManager.createQuery("from Cidade as c " +
													"where c.unidadeFederacao.id = :idUf " +
													"order by c.descricao");
			query.setParameter("idUf", unidadeFederacao.getId());
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de Cidade do Banco de Dados");
			Query query = entityManager.createQuery("from Cidade as c " +
													"order by c.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public Cidade buscarPorCidadeEstado(String cidade, String uf) throws Exception {
		try{
			
			Query query = entityManager.createQuery("from Cidade as c " +
													" where upper(c.descricao) = upper(:cidade) " +
													" and upper(c.unidadeFederacao.sigla) = upper(:unidadeFederacao) " +
													" order by c.id");
			
			query.setParameter("cidade", cidade);
			query.setParameter("unidadeFederacao", uf);
			
			if(query.getResultList().size()>0)
				return (Cidade) query.getResultList().get(0);
			else				
				return null;
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	
}
