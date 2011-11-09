package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Empresa;
import br.com.geostore.entity.Usuario;

@Name("empresaDAO")
public class EmpresaDAO {

	@In
	private EntityManager entityManager;
	
	@Logger	
	private Log log;

	public void incluir(Empresa empresa) throws Exception {
		try{
			log.info("Incluir Empresa: #0", empresa.getId());
			entityManager.persist(empresa);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(Empresa empresa) throws Exception {
		try{
			log.info("Alterar Empresa: #0", empresa.getDocumento());
			
			entityManager.merge(empresa);
			entityManager.merge(empresa.getEndereco());
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(Empresa empresa) throws Exception {
		try{
			log.info("Persistir Empresa: #0", empresa.getDocumento());
			entityManager.persist(empresa);	
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void excluir(Empresa empresa) throws Exception {
		try{
			if(!entityManager.contains(empresa))
				empresa = entityManager.merge(empresa);
			
			log.info("Remover Empresa: #0", empresa.getId());
			entityManager.remove(empresa);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Empresa buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(Empresa.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Empresa> buscarTodos(Usuario usuarioLogado) throws Exception {
		try{
			
			log.info("Buscando Lista de Empresas do Banco de Dados");
			Query query = entityManager.createQuery("from Empresa as e " +
													"order by e.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Empresa> buscarPorUsuarioLogado(Usuario usuarioLogado) throws Exception {
		try{
			
			log.info("Buscando Lista de Empresas do Banco de Dados vinculadas a " + usuarioLogado.getNome());
			
			String sQuery;			
			
			sQuery = " from Empresa as e ";			
			if(usuarioLogado.getTipoUsuario().getId().longValue() != 1) sQuery += " where e.id = :idEmpresaUsuario ";						
			sQuery += " order by e.id ";			
			
			Query query = entityManager.createQuery(sQuery);			
			if(usuarioLogado.getTipoUsuario().getId().longValue() != 1) query.setParameter("idEmpresaUsuario", usuarioLogado.getEmpresaVinculo().getId());
			
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	

	public boolean buscarPorCNPJ(Empresa empresa, String acao) throws Exception {
		try{
			
			log.info("Buscando se CNPJ já existe no Banco de Dados: " + empresa.getDocumento());
			
			String sQuery;			
			
			if(acao=="NOVA"){
				
				sQuery = " from Empresa as e ";	
				sQuery += " where e.documento = :empresaCNPJ ";
				sQuery += " order by e.id ";			
				
				Query query = entityManager.createQuery(sQuery);
				query.setParameter("empresaCNPJ", empresa.getDocumento());
				
				if(query.getResultList()==null || query.getResultList().isEmpty())				
					return false;	
					
			}
			
			if(acao=="EDITAR"){
				
				sQuery = " from Empresa as e ";	
				sQuery += " where e.documento = :empresaCNPJ ";
				sQuery += " and e.id <> :empresaId ";
				sQuery += " order by e.id ";					
				
				Query query = entityManager.createQuery(sQuery);
				query.setParameter("empresaCNPJ", empresa.getDocumento());
				query.setParameter("empresaId", empresa.getId());
				
				if(query.getResultList()==null || query.getResultList().isEmpty())				
					return false;	
			
			}
			
			return true;
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
}
