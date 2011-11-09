package br.com.geostore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.servlet.ContextualHttpServletRequest;
import org.jboss.seam.web.AbstractResource;

import br.com.geostore.email.controller.ReenvioSenhaController;


@Scope(ScopeType.APPLICATION)
@Name("recuperaSenhaServlet")
@BypassInterceptors
public class RecuperaSenhaServlet extends AbstractResource {

	private String email;
	private String senha;
	private String nome;
	
	@Override	
	public void getResource(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		new ContextualHttpServletRequest(request) {

			@Override
			public void process() {				
					
				String email = request.getParameter("email");
					
				ReenvioSenhaController r = (ReenvioSenhaController) Component.getInstance(ReenvioSenhaController.class);	
				r.setEmail(email);
				String retorno = r.send();
				
				try{
					
					PrintWriter out = response.getWriter();
					out.print(retorno);
					out.flush();
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}

		}.run();
	}

	@Override
	public String getResourcePath() {
		return "/recuperaSenhaServlet";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}