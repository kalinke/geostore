package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.VoucherDAO;
import br.com.geostore.entity.StatusVoucher;
import br.com.geostore.entity.Usuario;
import br.com.geostore.entity.Voucher;

@Name("voucherController")
@Scope(ScopeType.CONVERSATION)
public class VoucherController {

	@In(create=true) private VoucherDAO voucherDAO;

	@In private FacesMessages facesMessages;
	private Usuario usuarioLogado;
	
	private Voucher voucher = new Voucher();
	private String codigoVoucher;
	
	public VoucherController() {
		this.usuarioLogado = (Usuario) Contexts.getSessionContext().get("usuarioLogado");
	}
	
	public String cancelar(){		
		return "CANCELAR";
	}
	
	public void buscarVoucher() throws Exception{
		
		try {
			voucher = new Voucher();
			
			List<Voucher> voucherResgate = voucherDAO.buscarPorCodigo(usuarioLogado, codigoVoucher);
						
			if(voucherResgate.size() < 1)
				throw new RuntimeException("Nenhum voucher encontrado com o número informado!");
			
			voucher = voucherResgate.get(0);

		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}
	}

	
	public void resgatarVoucher() throws Exception{
		try {
			
			StatusVoucher statusVoucher = new StatusVoucher();
			
			statusVoucher.setId(2l);
			
			voucher.setStatusVoucher(statusVoucher);			
			voucherDAO.alterar(voucher);
			
			facesMessages.add("Voucher resgatado com sucesso!");
						
			
				
		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public String getCodigoVoucher() {
		return codigoVoucher;
	}

	public void setCodigoVoucher(String codigoVoucher) {
		this.codigoVoucher = codigoVoucher;
	}
	
	

	

	
}
