package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Usuario;

@Name("usuarioDAO")
@Transactional
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
	public List<Usuario> buscarTodos(Usuario usuarioLogado) throws Exception {
		try{
			
			log.info("Buscando Lista de Usuarios do Banco de Dados");
			
			String sQuery;
			
			sQuery = " from Usuario as u ";			
			if(usuarioLogado.getTipoUsuario().getId().longValue() != 1) sQuery += " where u.empresaVinculo.id = :idEmpresaUsuario ";						
			sQuery += " order by u.id ";
			
			Query query = entityManager.createQuery(sQuery);
			if(usuarioLogado.getTipoUsuario().getId().longValue() != 1) query.setParameter("idEmpresaUsuario", usuarioLogado.getEmpresaVinculo().getId());
													
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

	public boolean buscarPorCPF(Usuario usuario) throws Exception {
		try{
			
			log.info("Buscando se CPF já existe no Banco de Dados: " + usuario.getCpf());
			
			String sQuery;			
			
			sQuery = " from Usuario as u ";	
			sQuery += " where u.cpf = :usuarioCPF ";
			sQuery += " and u.id <> :usuarioID ";
			sQuery += " order by u.id ";			
			
			Query query = entityManager.createQuery(sQuery);			
			query.setParameter("usuarioCPF", usuario.getCpf());
			query.setParameter("usuarioID", usuario.getId());
			
			if(query.getResultList()==null || query.getResultList().isEmpty()){
				
				sQuery = " from Usuario as u ";	
				sQuery += " where u.cpf = :usuarioCPF  ";
				sQuery += " order by u.id ";			
				
				query = entityManager.createQuery(sQuery);
				query.setParameter("usuarioCPF", usuario.getCpf());
				
				if(query.getResultList()==null || query.getResultList().isEmpty())				
					return false;	
			}
			
			return true;
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	public boolean buscarPorEmail(Usuario usuario) throws Exception {
		try{
			
			log.info("Buscando se Email já existe no Banco de Dados: " + usuario.getEmail());
			
			String sQuery;			
			
			sQuery = " from Usuario as u ";	
			sQuery += " where u.email = :usuarioEmail ";
			sQuery += " and u.id <> :usuarioID ";
			sQuery += " order by u.id ";			
			
			Query query = entityManager.createQuery(sQuery);			
			query.setParameter("usuarioEmail", usuario.getEmail());
			query.setParameter("usuarioID", usuario.getId());
			
			if(query.getResultList()==null || query.getResultList().isEmpty()){
				
				sQuery = " from Usuario as u ";	
				sQuery += " where u.email = :usuarioEmail  ";
				sQuery += " order by u.id ";			
				
				query = entityManager.createQuery(sQuery);
				query.setParameter("usuarioEmail", usuario.getEmail());
				
				if(query.getResultList()==null || query.getResultList().isEmpty())				
					return false;	
			}
			
			return true;
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarReenvio(String email) throws Exception {
		try{
			
			log.info("Buscando se Email para reenvio de senha: " + email);
			
			String sQuery;			
			
			sQuery = " from Usuario as u ";	
			sQuery += " where u.email = :email ";
			sQuery += " order by u.id ";			
			
			Query query = entityManager.createQuery(sQuery);			
			query.setParameter("email", email);
			
			return query.getResultList();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
}
