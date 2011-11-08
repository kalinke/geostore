package br.com.geostore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;


@Entity
@Name("promocao")
@Table(name = "gs_promocao")
public class Promocao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="qde_voucher", length=100)
	private Long qde_voucher;
	
	@Column(name="qde_solicitada", length=100)
	private Long qde_solicitada;
	
	@Column(name="descricao")
	private String descricao;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	@OneToMany(mappedBy="promocao", cascade=CascadeType.ALL)
	private List<Voucher> vouchers;
	
	@ManyToOne
	@JoinColumn(name = "id_status_promocao")
	private StatusPromocao statusPromocao;
	
	public Promocao(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getQde_voucher() {
		return qde_voucher;
	}

	public void setQde_voucher(Long qde_voucher) {
		this.qde_voucher = qde_voucher;
	}

	public Long getQde_solicitada() {
		return qde_solicitada;
	}

	public void setQde_solicitada(Long qde_solicitada) {
		this.qde_solicitada = qde_solicitada;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Voucher> getVouchers() {
		return vouchers;
	}

	public void setVouchers(List<Voucher> vouchers) {
		this.vouchers = vouchers;
	}

	public StatusPromocao getStatusPromocao() {
		return statusPromocao;
	}

	public void setStatusPromocao(StatusPromocao statusPromocao) {
		this.statusPromocao = statusPromocao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
