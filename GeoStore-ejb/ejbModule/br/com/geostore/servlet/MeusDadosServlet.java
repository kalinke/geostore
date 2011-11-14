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

import br.com.geostore.dao.UsuarioDAO;
import br.com.geostore.entity.Usuario;
import br.com.geostore.entity.Voucher;

@Scope(ScopeType.APPLICATION)
@Name("meusDadosServlet")
@BypassInterceptors
public class MeusDadosServlet extends AbstractResource {

    @Override
    public void getResource(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {

        new ContextualHttpServletRequest(request) {
        	
        	@Override
            public void process(){       		        
        		
        		String idUsuario = request.getParameter("idUsuario");        		
        	        		
    			UsuarioDAO usuarioDao = (UsuarioDAO) Component.getInstance(UsuarioDAO.class);
    			
    			try {
					
    				Usuario usuario = usuarioDao.buscarPorId(Long.parseLong(idUsuario));
					
					List<Voucher> vouchers = usuario.getVouchers();
					
					JSONArray jVouchers = new JSONArray();
					
					for (Voucher voucher : vouchers) {
										
						JSONObject jAtributos = new JSONObject();
						
						jAtributos.put("nomeProduto", voucher.getPromocao().getProduto().getNome());
						jAtributos.put("descProduto",voucher.getPromocao().getProduto().getDescricao());
						jAtributos.put("precoProduto",voucher.getPromocao().getProduto().getValor());
						jAtributos.put("nomeLoja",voucher.getPromocao().getProduto().getLoja().getNomeFantasia());
						jAtributos.put("endLoja",voucher.getPromocao().getProduto().getLoja().getEndereco().getLogradouro());
						jAtributos.put("numLoja",voucher.getPromocao().getProduto().getLoja().getEndereco().getNumeroLogradouro());
						jAtributos.put("bairroLoja",voucher.getPromocao().getProduto().getLoja().getEndereco().getBairro());
						jAtributos.put("descPromocao",voucher.getPromocao().getDescricao());
						jAtributos.put("numVoucher",voucher.getCodigoVoucher());
						
						JSONObject jVoucher = new JSONObject();
						jVoucher.put("voucher", jAtributos);
						jVouchers.put(jVoucher);
						
					}
					
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print(jVouchers);
					out.flush();
						
    			} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }.run();
    }		    	
      
    @Override
    public String getResourcePath() {
        return "/meusDadosServlet";
    }    
}