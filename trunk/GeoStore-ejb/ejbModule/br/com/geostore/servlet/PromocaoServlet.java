package br.com.geostore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import br.com.geostore.dao.PromocaoDAO;
import br.com.geostore.dao.VoucherDAO;
import br.com.geostore.entity.Promocao;
import br.com.geostore.entity.StatusVoucher;
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
					
					//Pega os parametros da request
					Long idPromocao = Long.parseLong(request.getParameter("idPromocao"));
					Long idUsuario  = Long.parseLong(request.getParameter("idUsuario"));					
									
					VoucherDAO vDao = (VoucherDAO) Component.getInstance(VoucherDAO.class);
					JSONObject j = new JSONObject();
					
					//Verifica se já existe um voucher gerado para o usuário
					List<Voucher> voucherList = vDao.buscarPorUsuario(idPromocao, idUsuario);
					
					if (voucherList==null || voucherList.size()<=0){										
						
						PromocaoDAO pDao = (PromocaoDAO) Component.getInstance(PromocaoDAO.class);						
						Promocao promocao = pDao.buscarPorId(idPromocao);										
						
						//Verifica se a promoção possui saldo
						if (promocao!=null && promocao.getQdeVoucher()-promocao.getQdeSolicitada()>0){
							
							//Atualiza o saldo da promoção
							promocao.setQdeSolicitada(promocao.getQdeSolicitada()+1);
							pDao.salvar(promocao);
							
							//Cria o voucher e suas dependencias 
							Voucher voucher = new Voucher();
							
							Usuario usuario = new Usuario();
							usuario.setId(idUsuario);
														
							voucher.setPromocao(promocao);
							voucher.setUsuario(usuario);
							
							String numVoucher = String.valueOf(idPromocao) + String.valueOf(idUsuario ) + String.valueOf(promocao.getProduto().getId());							
							voucher.setCodigoVoucher(numVoucher);
						
							StatusVoucher status = new StatusVoucher();
							status.setId(1l);
							voucher.setStatusVoucher(status);
							
							//Inclui o voucher							
							vDao.incluir(voucher);
														
							j.put("voucher",  numVoucher);
							j.put("mensagem", "");							
						
						}else{
							
							j.put("voucher", "0");
							j.put("mensagem", "Não existe mais saldo para esta promoção.");
							
						}
											
					}else{
					
						j.put("voucher", "0");
						j.put("mensagem", "Não é permitido gerar mais de 1 (um) voucher por promoção.");
				
					}
					
					//Retorna os dados
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