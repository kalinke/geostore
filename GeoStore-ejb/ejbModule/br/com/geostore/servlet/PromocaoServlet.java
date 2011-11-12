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

import br.com.geostore.dao.VoucherDAO;
import br.com.geostore.entity.Produto;
import br.com.geostore.entity.Promocao;
import br.com.geostore.entity.Usuario;
import br.com.geostore.entity.Voucher;

@Scope(ScopeType.APPLICATION)
@Name("promocaoServlet")
@BypassInterceptors
public class PromocaoServlet extends AbstractResource {

	@Override
	public void getResource(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		new ContextualHttpServletRequest(request) {

			@Override
			public void process() {
				
				try {
					
					String idProduto  = request.getParameter("idProduto");
					String idPromocao = request.getParameter("idPromocao");
					String idUsuario  = request.getParameter("idUsuario");
									
					Produto produto = new Produto();
					produto.setId(Long.parseLong(idProduto));
					
					Promocao promocao = new Promocao();
					promocao.setId(Long.parseLong(idPromocao));
					promocao.setProduto(produto);				
					
					Usuario usuario = new Usuario();
					usuario.setId(Long.parseLong(idUsuario));
					
					Voucher voucher = new Voucher();					
					voucher.setPromocao(promocao);
					voucher.setUsuario(usuario);
					voucher.setCodigo_voucher(idProduto.concat(idPromocao.concat(idUsuario)));
					
					VoucherDAO vDao = (VoucherDAO) Component.getInstance(VoucherDAO.class);
					vDao.incluir(voucher);
					
					JSONObject j = new JSONObject();
					j.put("voucher", voucher.getCodigo_voucher());
				
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
		return "/promocaoServlet";
	}
}