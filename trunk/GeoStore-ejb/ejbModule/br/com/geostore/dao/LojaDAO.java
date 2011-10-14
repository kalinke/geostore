package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Loja;

@Name("lojaDAO")
public class LojaDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;

	public void incluir(Loja loja) throws Exception {
		try{
			log.info("Incluir Loja: #0", loja.getId());
			entityManager.persist(loja);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(Loja loja) throws Exception {
		try{
			log.info("Alterar Loja: #0", loja.getDocumento());
			
			entityManager.merge(loja);
			entityManager.merge(loja.getEndereco());
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(Loja loja) throws Exception {
		try{
			log.info("Persistir loja: #0", loja.getDocumento());
			entityManager.persist(loja);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void excluir(Loja loja) throws Exception {
		try{
			if(!entityManager.contains(loja))
				loja = entityManager.merge(loja);
			
			log.info("Remover Empresa: #0", loja.getId());
			entityManager.remove(loja);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Loja buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(Loja.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Loja> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de Loja do Banco de Dados");
			Query query = entityManager.createQuery("from Loja as l " +
													"order by l.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	
}
