package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Endereco;

@Name("enderecoDAO")
public class EnderecoDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;

	public void incluir(Endereco endereco) throws Exception {
		try{
			log.info("Incluir Endereco: #0", endereco.getId());
			entityManager.persist(endereco);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(Endereco endereco) throws Exception {
		try{
			log.info("Alterar Endereco: #0", endereco.getLogradouro());
			entityManager.merge(endereco);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(Endereco endereco) throws Exception {
		try{
			log.info("Persistir Endereco: #0", endereco.getLogradouro());
			entityManager.persist(endereco);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(List<Endereco> enderecos) throws Exception {
		try {
			for (Endereco endereco : enderecos) {
				entityManager.persist(endereco);
				entityManager.flush();
			}
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	public void excluir(Endereco endereco) throws Exception {
		try{
			if(!entityManager.contains(endereco))
				endereco = entityManager.merge(endereco);
			
			
			log.info("Remover Endereco: #0", endereco.getId());
			entityManager.remove(endereco);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Endereco buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(Endereco.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> buscarPorEmpresa(Long idEmpresa) throws Exception {
		try{
			
			log.info("Buscando Lista de Enderecos do Banco de Dados da empresa " + idEmpresa);
			Query query = entityManager.createQuery("from Endereco as e " +
													" where e.empresa.id = :idEmpresa " + 
													"order by e.id");
			query.setParameter("idEmpresa", idEmpresa);
			
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de Enderecos do Banco de Dados");
			Query query = entityManager.createQuery("from Endereco as e " +
													"order by e.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	
}
