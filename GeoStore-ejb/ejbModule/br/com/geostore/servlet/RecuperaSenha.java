package br.com.geostore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.servlet.ContextualHttpServletRequest;
import org.jboss.seam.web.AbstractResource;

import br.com.geostore.dao.UsuarioDAO;


@Scope(ScopeType.APPLICATION)
@Name("recuperaSenha")
@BypassInterceptors
public class RecuperaSenha extends AbstractResource {

	@Override	
	public void getResource(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		new ContextualHttpServletRequest(request) {

			@Override
			public void process() {
				
				String email = request.getParameter("email");
				
				Renderer r = (Renderer) Component.getInstance(Renderer.class);
				
				r.render("/EmailLayout/ReenvioSenhaLayout.xhtml");
				
				
				
			}

		}.run();
	}

	@Override
	public String getResourcePath() {
		return "/recuperaSenha";
	}
}