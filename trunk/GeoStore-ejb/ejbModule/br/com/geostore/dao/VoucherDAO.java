package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Usuario;
import br.com.geostore.entity.Voucher;

@Name("voucherDAO")
@Transactional(TransactionPropagationType.REQUIRED)
public class VoucherDAO {

	@In(create=true)
	private EntityManager entityManager;
	
	@Logger
	private Log log;
	
	public void incluir(Voucher voucher) throws Exception {
		try{
			log.info("Incluir Voucher: #0", voucher.getId());
			entityManager.persist(voucher);
			entityManager.flush();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void alterar(Voucher voucher) throws Exception {
		try{
			
			log.info("Alterar voucher: #0", voucher.getId());			
			entityManager.merge(voucher);
			entityManager.flush();			
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void salvar(Voucher voucher) throws Exception {
		try{
			log.info("Persistir Voucher: #0", voucher.getId());
			entityManager.persist(voucher);	
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
	public void excluir(Voucher voucher) throws Exception {
		try{
			if(!entityManager.contains(voucher))
				voucher = entityManager.merge(voucher);
			
			log.info("Remover Voucher: #0", voucher.getId());
			entityManager.remove(voucher);
			entityManager.flush();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Voucher buscarPorId(Long id) throws Exception {
		try{
			return entityManager.find(Voucher.class, id);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Voucher> buscarPorCodigo(Usuario usuarioLogado, String codigoVoucher) throws Exception {
		try{
			boolean usuarioAdministrador = (usuarioLogado.getTipoUsuario().getId()==1?true:false);
			log.info("Buscando Lista de Voucher para Resgate do Banco de Dados");
			
			String sQuery = "from Voucher as v " +
				" where v.codigoVoucher = :codigoVoucher " +
				" and v.statusVoucher.id = 1";
			if(!usuarioAdministrador) sQuery += " and v.promocao.produto.loja.empresaSuperior.id = :idEmpresaUsuarioLogado ";	
			
			sQuery += "order by v.id";
				
			Query query = entityManager.createQuery(sQuery);
			
			query.setParameter("codigoVoucher", codigoVoucher);
			
			if(!usuarioAdministrador) query.setParameter("idEmpresaUsuarioLogado", usuarioLogado.getEmpresaVinculo().getId());
			
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
			
	@SuppressWarnings("unchecked")
	public List<Voucher> buscarTodos() throws Exception {
		try{
			
			log.info("Buscando Lista de Voucher do Banco de Dados");
			Query query = entityManager.createQuery("from Voucher as v " +
													"order by v.id");
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Voucher> buscarPorUsuario(Long idPromocao, Long idUsuario) throws Exception {
		try{
			
			log.info("Buscando Voucher do Banco de Dados");
			Query query = entityManager.createQuery("from Voucher as v " +
													"where id_promocao = :promocao " +
													"and id_usuario = :usuario");
			query.setParameter("promocao", idPromocao);
			query.setParameter("usuario", idUsuario);
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	 
}
