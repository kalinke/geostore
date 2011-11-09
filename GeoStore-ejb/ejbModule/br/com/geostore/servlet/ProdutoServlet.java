package br.com.geostore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.servlet.ContextualHttpServletRequest;
import org.jboss.seam.web.AbstractResource;

import br.com.geostore.dao.ProdutoDAO;
import br.com.geostore.entity.Endereco;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.Produto;
import br.com.geostore.entity.Promocao;


@Scope(ScopeType.APPLICATION)
@Name("produtoServlet")
@BypassInterceptors
public class ProdutoServlet extends AbstractResource {

    @Override
    public void getResource(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {

        new ContextualHttpServletRequest(request) {
        	
        	@Override
            public void process(){       		
        		
        		String texto = request.getParameter("texto");
        		double lat   = Double.parseDouble(request.getParameter("lat"));
        		double log   = Double.parseDouble(request.getParameter("log"));        		
        		double raio  = Double.parseDouble(request.getParameter("raio"));
        		
        		if (texto != null){
        			        			
        			ProdutoDAO pDao = (ProdutoDAO) Component.getInstance(ProdutoDAO.class);		        			
        			try {
						List<Produto> p = pDao.buscarPorProximidade(texto, lat, log, raio);
						
						if (p != null){
							
							JSONArray  jArrayProdutos = new JSONArray();
							JSONArray  jArrayPromos   = null;							
							JSONObject jObjeto        = null;
							JSONObject jAtributos     = null;							
							
							for (Produto produto : p) {
								
								Loja loja = produto.getLoja();
								Endereco end = loja.getEndereco();								
								List<Promocao> promo = produto.getPromocoes();
								
								jArrayPromos = new JSONArray();
																
								for (Promocao promocao : promo) {
									
									jAtributos = new JSONObject();									
									jAtributos.put("idPromo", promocao.getId());
									jAtributos.put("descPromo", promocao.getDescricao());
									jAtributos.put("idProduto", promocao.getProduto());
									jAtributos.put("qtdeSolic", promocao.getQde_solicitada());
									jAtributos.put("qtdeVouch", promocao.getQde_voucher());
									
									jObjeto = new JSONObject();
									jObjeto.put("promocao", jAtributos);
									
									jArrayPromos.put(jObjeto);
									
								}
																
								jAtributos = new JSONObject();
								
								jAtributos.put("idProd",    produto.getId());
								jAtributos.put("nomeProd",  produto.getNome());
								jAtributos.put("descProd",  produto.getDescricao());
								jAtributos.put("prcProd",   produto.getValor());								
								jAtributos.put("idLoja",    loja.getId());
								jAtributos.put("nomeLoja",  loja.getNomeFantasia());
								jAtributos.put("foneLoja",  loja.getTelefone());
								jAtributos.put("endLoja",   end.getLogradouro());								
								jAtributos.put("bairroLoja",end.getBairro());
								jAtributos.put("logLoja",   end.getLongitude());
								jAtributos.put("latLoja",   end.getLatitude());
								jAtributos.put("promocoes", jArrayPromos);
															
								jObjeto = new JSONObject();
								jObjeto.put("produto", jAtributos);
								jArrayProdutos.put(jObjeto);
							}
							
							JSONObject jResultado = new JSONObject();
							jResultado.put("produtos", jArrayProdutos);
							response.setContentType("application/json");
							PrintWriter out = response.getWriter();
							out.print(jResultado);
							out.flush();
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}        			
        		}
        	}
        }.run();
    }		    	
      
    @Override
    public String getResourcePath() {
        return "/produtoServlet";
    }    
}