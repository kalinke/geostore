package br.com.geostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.geostore.entity.Produto;
import br.com.geostore.entity.Usuario;

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
	public List<Produto> buscarTodos(Usuario usuarioLogado) throws Exception {
		try{
			
			String sQuery;
			log.info("Buscando Lista de Produto do Banco de Dados");
			
			sQuery = " from Produto as p ";			
			if(usuarioLogado.getTipoUsuario().getId().longValue() != 1) sQuery += " where p.loja.empresaSuperior.id = :idEmpresaUsuario ";						
			sQuery += " order by p.id ";
			
			Query query = entityManager.createQuery(sQuery);
			if(usuarioLogado.getTipoUsuario().getId().longValue() != 1) query.setParameter("idEmpresaUsuario", usuarioLogado.getEmpresaVinculo().getId());
													
			return query.getResultList();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> buscarPorProximidade(String texto, double lat, double log, double raio) throws Exception {
		try{
						
			String sQry = "";
			sQry += " select p.* ";			
			sQry += " from gs_produtos as p, gs_lojas as l, gs_enderecos as e ";
			sQry += " where p.id_loja = l.id ";
			sQry += " and l.id_endereco = e.id_endereco ";
			sQry += " and p.id_status_produto = 1 ";
			sQry += " and (p.descricao = :texto or p.nome = :texto) ";
			
			if (raio!=0 && lat!=0 && log!=0){
				sQry += " and calcDistCoord(:lat,:log,e.latitude,e.longitude) <= :raio";
				sQry += " order by calcDistCoord(:lat,:log,e.latitude,e.longitude)";
			}else{
				sQry += " order by p.nome ";
			}
			
			Query query = entityManager.createNativeQuery(sQry, Produto.class);
			query.setParameter("texto", texto);
			
			if (raio!=0 && lat!=0 && log!=0){
				query.setParameter("lat", lat);
				query.setParameter("log", log);
				query.setParameter("raio", raio);
			}
												
			return query.getResultList();
			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
}
