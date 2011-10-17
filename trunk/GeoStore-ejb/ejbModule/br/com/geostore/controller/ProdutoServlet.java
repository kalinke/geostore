package br.com.geostore.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.servlet.ContextualHttpServletRequest;
import org.jboss.seam.web.AbstractResource;

import br.com.geostore.entity.Buscar;
import br.com.geostore.entity.Produto;


@Scope(ScopeType.APPLICATION)
@Name("produtoServlet")
@BypassInterceptors
public class ProdutoServlet extends AbstractResource {

    @Override
    public void getResource(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {

        new ContextualHttpServletRequest(request) {
        	
        	@Override
            public void process(){       		
				try {
					
					HttpServletRequest req = request;			
					HttpServletResponse res = response;
					
					Buscar b = null;                
	                
	                //Recebe o objeto 
	                ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
	                
	                //Converte o objeto
	                try{
	                        b = (Buscar) ois.readObject();
	                }catch (Exception e) {
	                        e.printStackTrace();
	                }               
	                
	                if (b!=null){
	                        
	                        //Cria uma lista para retornar pela request
	                        List<Produto> l = null; 
	                        
	                        Produto p = new Produto();	                        
	                        p.setDescricao("TENIS");
	                        
	                        List<Produto> listP = new ArrayList<Produto>();
	                        listP.add(p);
	                        
	                        
	                        //Efetua a localização no banco de dados
	                        if(b.getTexto().toUpperCase().equals("TENIS")){
	                                l = listP;
	                        }else if (b.getTexto().toUpperCase().equals("LANCHE")){
	                                l = listP;
	                        }
	                        
	                        //Devolve a lista para request
	                        ObjectOutputStream ous = new ObjectOutputStream(res.getOutputStream());
	                        ous.writeObject(l);
	                        ous.flush();
	                        ous.close();                    
	                }
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        	
        }.run();
    }		    	
  
    

    @Override
    public String getResourcePath() {
        return "/produtoServlet";
    }
    
    
}