package br.com.geostore.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.Component;

import br.com.geostore.dao.ProdutoDAO;
import br.com.geostore.entity.Buscar;
import br.com.geostore.entity.Produto;



/**
 * Servlet implementation class BuscarProdutosServlet
 */

public class BuscarProdutosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarProdutosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);     
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
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
			
			try {
				l = pegarProdutos();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//Devolve a lista para request
			ObjectOutputStream ous = new ObjectOutputStream(res.getOutputStream());
			ous.writeObject(l);
			ous.flush();
			ous.close();			
		}
	}
	
	private List<Produto> pegarProdutos(){
		try {
			
			org.jboss.seam.contexts.Lifecycle.beginCall();
			ProdutoDAO produtoDAO = (ProdutoDAO) Component.getInstance(ProdutoDAO.class);		
			org.jboss.seam.contexts.Lifecycle.endCall();
			
			
			return produtoDAO.buscarTodos();
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}
	
}
