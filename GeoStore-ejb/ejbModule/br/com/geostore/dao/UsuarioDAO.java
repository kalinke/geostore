package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Usuario;

@Name("usuarioDAO")
public class UsuarioDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;

	public void incluir(Usuario usuario) throws Exception {
		try{
			log.info("Incluir Usuario: #0", usuario.getId());
			entityManager.persist(usuario);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(Usuario usuario) throws Exception {
		try{
			log.info("Alterar Usuario: #0", usuario.getId());			
			entityManager.merge(usuario);
			entityManager.flush();			
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(Usuario usuario) throws Exception {
		try{
			log.info("Persistir usuario: #0", usuario.getId());
			entityManager.persist(usuario);		
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void excluir(Usuario usuario) throws Exception {
		try{
			if(!entityManager.contains(usuario))
				usuario = entityManager.merge(usuario);
			
			log.info("Remover Empresa: #0", usuario.getId());
			entityManager.remove(usuario);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Usuario buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(Usuario.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de Usuarios do Banco de Dados");
			Query query = entityManager.createQuery("from Usuario as u " +
													"order by u.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Usuario buscarAutenticacao(Usuario usuario) throws Exception {
		try {
			
			Query query = entityManager.createQuery("from Usuario as u "					
					+ " where u.email = :email "				
					+ " and   u.senha = :senha" );

			query.setParameter("email", usuario.getEmail());
			query.setParameter("senha", usuario.getSenha());
		
			return (Usuario) (query.getResultList().size() < 1 ? null : query.getResultList().get(0));
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
}
