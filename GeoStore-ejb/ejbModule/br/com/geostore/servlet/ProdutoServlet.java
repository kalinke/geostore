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
import org.jfree.util.Log;

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
        		String log   = request.getParameter("log");
        		String lat   = request.getParameter("lat");
        		
        		Log.info("Parametro 'texto': " + texto);
        		Log.info("Parametro 'log'  : " + log);
        		Log.info("Parametro 'lat'  : " + lat);
        		
        		if (texto != null){
        			        			
        			ProdutoDAO pDao = (ProdutoDAO) Component.getInstance(ProdutoDAO.class);		        			
        			try {
						List<Produto> p = pDao.buscarPorDescricao(texto);
						Log.info("Numero de produtos encontratos: " + p.size());
						
						if (p != null){
							JSONArray jArray = new JSONArray();
							
							for (Produto produto : p) {
								
								Loja loja = produto.getLoja();
								Endereco end = loja.getEndereco();
								List<Promocao> promo = produto.getPromocoes();
								
								int existPromo = 0;
								if (promo!=null && promo.size()>0){
									existPromo = 1;
									Log.info("Numero de promocoes para o produto: " + produto.getId() + "/" + existPromo);
								}
																
								jArray.put(new JSONObject().put("idProd",    produto.getId()));
								jArray.put(new JSONObject().put("nomeProd",  produto.getNome()));
								jArray.put(new JSONObject().put("idLoja",    loja.getId()));
								jArray.put(new JSONObject().put("nomeLoja",  loja.getNomeFantasia()));
								jArray.put(new JSONObject().put("endLoja",   end.getLogradouro()));
								jArray.put(new JSONObject().put("bairroLoja",end.getBairro()));
								jArray.put(new JSONObject().put("prcProd",   produto.getValor()));
								jArray.put(new JSONObject().put("promo",     existPromo));
								
							}
							
							JSONObject j = new JSONObject();
							j.put("produtos", jArray);
							response.setContentType("application/json");
							PrintWriter out = response.getWriter();
							out.print(j);
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