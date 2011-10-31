package br.com.geostore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.codehaus.jettison.json.JSONObject;
import org.jboss.seam.Component;

import br.com.geostore.dao.ProdutoDAO;
import br.com.geostore.dao.UsuarioDAO;
import br.com.geostore.entity.Usuario;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);	
			
		UsuarioDAO udao = new UsuarioDAO();
		JSONObject js = new JSONObject();		
			
		try {
			org.jboss.seam.contexts.Lifecycle.beginCall();
			UsuarioDAO u = (UsuarioDAO) Component.getInstance(UsuarioDAO.class);	
			org.jboss.seam.contexts.Lifecycle.endCall();
			Usuario us = u.buscarAutenticacao(usuario);
			if(us != null){
				js.put("Logado", 1);
			}else{
				js.put("Logado", 0);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		 // Pega a Stream de Saída do servidor que sera utilizada para enviar a resposta JSON
		out.print(js);//Escreve a resposta no formato JSON na Stream de saída que será recebida pela aplicação cliente
		out.flush();
	}
}
