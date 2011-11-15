package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.HistoricoBuscas;

@Transactional
@Name("historicoBuscasDAO")
public class HistoricoBuscasDAO {

	@In
	private EntityManager entityManager;
	
	@Logger
	private Log log;

	public void incluir(HistoricoBuscas historicoBuscas) throws Exception {
		try{
			log.info("Incluir HistoricoBuscas: #0", historicoBuscas.getId());
			entityManager.persist(historicoBuscas);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(HistoricoBuscas historicoBuscas) throws Exception {
		try{
			log.info("Alterar tipoEmpresa: #0", historicoBuscas.getId());			
			entityManager.merge(historicoBuscas);
			entityManager.flush();			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(HistoricoBuscas historicoBuscas) throws Exception {
		try{
			log.info("Persistir historicoBuscas: #0", historicoBuscas.getId());
			entityManager.persist(historicoBuscas);	
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void excluir(HistoricoBuscas historicoBuscas) throws Exception {
		try{
			if(!entityManager.contains(historicoBuscas))
				historicoBuscas = entityManager.merge(historicoBuscas);
			
			log.info("Remover TipoEmpresa: #0", historicoBuscas.getId());
			entityManager.remove(historicoBuscas);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public HistoricoBuscas buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(HistoricoBuscas.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<HistoricoBuscas> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de HistoricoBuscas do Banco de Dados");
			Query query = entityManager.createQuery("from HistoricoBuscas as h " +
													"order by h.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<HistoricoBuscas> buscarUltimas50() throws Exception {
		try{
			
			log.info("Buscando Lista de HistoricoBuscas do Banco de Dados");
			Query query = entityManager.createQuery("from HistoricoBuscas as h " +
													"order by h.id");
			query.setMaxResults(50);
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
}
