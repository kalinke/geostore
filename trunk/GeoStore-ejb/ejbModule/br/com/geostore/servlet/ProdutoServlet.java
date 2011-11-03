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
        		
        		System.out.println("Parametro 'texto': " + texto);
        		System.out.println("Parametro 'lat'  : " + lat);
        		System.out.println("Parametro 'log'  : " + log);        		
        		System.out.println("Parametro 'raio' : " + raio);
        		
        		if (texto != null){
        			        			
        			ProdutoDAO pDao = (ProdutoDAO) Component.getInstance(ProdutoDAO.class);		        			
        			try {
						List<Produto> p = pDao.buscarPorProximidade(texto, lat, log, raio);
						System.out.println("Numero de produtos encontratos: " + p.size());
						
						if (p != null){
							
							JSONArray jArray = new JSONArray();
							
							for (Produto produto : p) {
								
								Loja loja = produto.getLoja();
								Endereco end = loja.getEndereco();
								List<Promocao> promo = produto.getPromocoes();
								
								int existPromo = 0;
								if (promo!=null && promo.size()>0){
									existPromo = 1;
									System.out.println("Numero de promocoes para o produto: " + produto.getId() + "/" + existPromo);
								}
																
								JSONObject j = new JSONObject();
								
								j.put("idProd",    produto.getId());
								j.put("nomeProd",  produto.getNome());
								j.put("descProd",  produto.getDescricao());
								j.put("prcProd",   produto.getValor());								
								j.put("idLoja",    loja.getId());
								j.put("nomeLoja",  loja.getNomeFantasia());
								j.put("foneLoja",  loja.getTelefone());
								j.put("endLoja",   end.getLogradouro());								
								j.put("bairroLoja",end.getBairro());
								j.put("logLoja",   end.getLongitude());
								j.put("latLoja",   end.getLatitude());
								j.put("promo",     existPromo);
															
								JSONObject jObj = new JSONObject();
								jObj.put("produto", j);
								jArray.put(jObj);
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