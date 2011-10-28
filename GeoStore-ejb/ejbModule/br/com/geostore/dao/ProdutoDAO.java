package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Produto;

@Name("produtoDAO")
public class ProdutoDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;

	public void incluir(Produto produto) throws Exception {
		try{
			log.info("Incluir Produto: #0", produto.getId());
			entityManager.persist(produto);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(Produto produto) throws Exception {
		try{
			log.info("Alterar produto: #0", produto.getId());			
			entityManager.merge(produto);
			entityManager.flush();			
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(Produto produto) throws Exception {
		try{
			log.info("Persistir Produto: #0", produto.getId());
			entityManager.persist(produto);	
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void excluir(Produto produto) throws Exception {
		try{
			if(!entityManager.contains(produto))
				produto = entityManager.merge(produto);
			
			log.info("Remover Produto: #0", produto.getId());
			entityManager.remove(produto);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Produto buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(Produto.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Produto> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de Produto do Banco de Dados");
			Query query = entityManager.createQuery("from Produto as u " +
													"order by u.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

}
