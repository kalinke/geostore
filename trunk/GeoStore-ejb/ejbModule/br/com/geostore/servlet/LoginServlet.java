package br.com.geostore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.servlet.ContextualHttpServletRequest;
import org.jboss.seam.web.AbstractResource;

import br.com.geostore.dao.UsuarioDAO;
import br.com.geostore.entity.Usuario;

@Scope(ScopeType.APPLICATION)
@Name("loginServlet")
@BypassInterceptors
public class LoginServlet extends AbstractResource {

    @Override
    public void getResource(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {

        new ContextualHttpServletRequest(request) {
        	
        	@Override
            public void process(){       		        
        		
        		String email = request.getParameter("email");
        		String senha = request.getParameter("senha");
        	        		
    			UsuarioDAO uDao = (UsuarioDAO) Component.getInstance(UsuarioDAO.class);
    			Usuario u = new Usuario();
    			u.setEmail(email);
    			u.setSenha(senha);
    			
    			try {
					u = uDao.buscarAutenticacao(u);
					JSONObject j = new JSONObject();
					
					if (u != null){						
							
						j.put("id",    u.getId());
						j.put("nome",  u.getNome());
						j.put("cpf",   u.getCpf());
						j.put("email", u.getEmail());
						j.put("senha", u.getSenha());
						
					}
						
					j.put("encontrou", u != null);
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print(j);
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}									
        	}
        }.run();
    }		    	
      
    @Override
    public String getResourcePath() {
        return "/loginServlet";
    }    
}