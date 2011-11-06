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
import br.com.geostore.entity.TipoUsuario;
import br.com.geostore.entity.Usuario;

@Scope(ScopeType.APPLICATION)
@Name("novoUsuarioServlet")
@BypassInterceptors
public class NovoUsuarioServlet extends AbstractResource {

	@Override
	public void getResource(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		new ContextualHttpServletRequest(request) {

			@Override
			public void process() {

				Usuario u = new Usuario();
				u.setNome(request.getParameter("nome"));
				u.setCpf(request.getParameter("cpf"));
				u.setEmail(request.getParameter("email"));
				u.setSenha(request.getParameter("senha"));
				TipoUsuario t = new TipoUsuario();
				t.setId(3L);
				u.setTipoUsuario(t);
				
				int incluiu = 0;

				try {
					UsuarioDAO uDao = (UsuarioDAO) Component.getInstance(UsuarioDAO.class);

					if (uDao.buscarPorCPF(u)) {
						incluiu = 1;
					} else if (uDao.buscarPorEmail(u)) {
						incluiu = 2;
					} else {
						uDao.incluir(u);
					}

					JSONObject j = new JSONObject();
					j.put("incluiu", incluiu);
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
		return "/novoUsuarioServlet";
	}
}