package br.com.geostore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.servlet.ContextualHttpServletRequest;
import org.jboss.seam.web.AbstractResource;


@Scope(ScopeType.APPLICATION)
@Name("produtoServlet")
@BypassInterceptors
public class ProdutoServlet extends AbstractResource {

    @Override
    public void getResource(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {

        new ContextualHttpServletRequest(request) {
        	
        	@Override
            public void process(){       		
        		
        		HashMap<String,String> hm = new HashMap<String,String>();
        		hm.put("message","My first JSON application");
        		
        		//Cada chave do HashMap vira uma Chave do JSON        		
        		JSONObject json = JSONObject.fromObject(hm);        	        		
        		response.setContentType("application/json");        		 
        		
        		PrintWriter out;
				try {
					out = response.getWriter();
					// Pega a Stream de Saída do servidor que sera utilizada para enviar a resposta JSON        		
	        		out.print(json);//Escreve a resposta no formato JSON na Stream de saída que será recebida pela aplicação cliente        		
	        		out.flush();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		
        		
        		/*JSONObject jObj = null;
            	JSONArray jArray = new JSONArray();
            	try {
            		//Cria uma lista para retornar pela request
        			List<Produto> l = null; 
        			
        			//Recebe os parametros da request
        			String texto = request.getParameter("texto");
        			//Double log = request.getParameter("log");
        			//Double lat = request.getParameter("lat");
        			
        			//Instancia a classe produtoDAO
        			ProdutoDAO produtoDAO = (ProdutoDAO) Component.getInstance(ProdutoDAO.class);		
        			
        		  	l = produtoDAO.buscarPorDescricao(texto);
        			
            		for (Produto produto : l) {
            			jObj = new JSONObject();
            			jObj.put("descricao", produto.getDescricao());
            			jArray.put(jObj);
        			};
        			
        			jObj = new JSONObject();
        			jObj.put("produtos", jArray);
        		
        		} catch (JSONException e) {
        			e.printStackTrace();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		
            	PrintWriter out;
				try {
					out = response.getWriter();
					out.print(jObj);
	            	out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}*/            	
        	}
        }.run();
    }		    	
  
    

    @Override
    public String getResourcePath() {
        return "/produtoServlet";
    }
    
    
}