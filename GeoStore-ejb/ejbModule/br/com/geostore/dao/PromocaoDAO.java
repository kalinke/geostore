package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Promocao;
import br.com.geostore.entity.Usuario;

@Name("promocaoDAO")
public class PromocaoDAO {
	
	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;
	
	public void incluir(Promocao promocao) throws Exception {
		try{
			log.info("Incluir Promocao: #0", promocao.getId());
			entityManager.persist(promocao);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(Promocao promocao) throws Exception {
		try{
			log.info("Alterar promocao: #0", promocao.getId());			
			entityManager.merge(promocao);
			entityManager.flush();			
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void salvar(Promocao promocao) throws Exception {
		try{
			log.info("Persistir Promocao: #0", promocao.getId());
			entityManager.persist(promocao);	
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void excluir(Promocao promocao) throws Exception {
		try{
			if(!entityManager.contains(promocao))
				promocao = entityManager.merge(promocao);
			
			log.info("Remover Promocao: #0", promocao.getId());
			entityManager.remove(promocao);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Promocao buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(Promocao.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> buscarTodos(Usuario usuarioLogado) throws Exception {
		try{
			
			String sQuery;
			log.info("Buscando Lista de Promoções do Banco de Dados");
			
			sQuery = " from Promocao as p ";	
			if(usuarioLogado.getTipoUsuario().getId().longValue() != 1) sQuery += " where p.produto.loja.empresaSuperior.id = :idEmpresaUsuario ";						
			sQuery += " order by p.id ";
			
			Query query = entityManager.createQuery(sQuery);
			if(usuarioLogado.getTipoUsuario().getId().longValue() != 1) query.setParameter("idEmpresaUsuario", usuarioLogado.getEmpresaVinculo().getId());
													
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

}
