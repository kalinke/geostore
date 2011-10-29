package br.com.geostore.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.geostore.entity.Buscar;
import br.com.geostore.entity.Empresa;
import br.com.geostore.entity.Produto;

/**
 * Servlet implementation class BuscarCoord
 */
public class BuscarCoord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarCoord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
			Empresa e = new Empresa();
			e.getEndereco().setLatitude(-25.375837350493054);
			e.getEndereco().setLongitude(-49.264692888709185);
			
			
		
			
			//Devolve a lista para request
			ObjectOutputStream ous = new ObjectOutputStream(res.getOutputStream());
			ous.writeObject(e);
			ous.flush();
			ous.close();			
		}
	

}
