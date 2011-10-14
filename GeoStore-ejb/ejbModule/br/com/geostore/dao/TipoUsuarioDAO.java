package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.TipoUsuario;

@Name("tipoUsuarioDAO")
public class TipoUsuarioDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;

	public void incluir(TipoUsuario tipoUsuario) throws Exception {
		try{
			log.info("Incluir TipoUsuario: #0", tipoUsuario.getId());
			entityManager.persist(tipoUsuario);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(TipoUsuario tipoUsuario) throws Exception {
		try{
			log.info("Alterar tipoEmpresa: #0", tipoUsuario.getId());			
			entityManager.merge(tipoUsuario);
			entityManager.flush();			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(TipoUsuario tipoUsuario) throws Exception {
		try{
			log.info("Persistir tipoUsuario: #0", tipoUsuario.getId());
			entityManager.persist(tipoUsuario);	
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void excluir(TipoUsuario tipoUsuario) throws Exception {
		try{
			if(!entityManager.contains(tipoUsuario))
				tipoUsuario = entityManager.merge(tipoUsuario);
			
			log.info("Remover TipoEmpresa: #0", tipoUsuario.getId());
			entityManager.remove(tipoUsuario);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public TipoUsuario buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(TipoUsuario.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<TipoUsuario> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de TipoUsuario do Banco de Dados");
			Query query = entityManager.createQuery("from TipoUsuario as t " +
													"order by t.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	
}
