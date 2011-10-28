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
	private int qde_voucher;
	
	@Column(name="qde_solicitada", length=100)
	private int qde_solicitada;
	
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

	public int getQde_voucher() {
		return qde_voucher;
	}

	public void setQde_voucher(int qde_voucher) {
		this.qde_voucher = qde_voucher;
	}

	public int getQde_solicitada() {
		return qde_solicitada;
	}

	public void setQde_solicitada(int qde_solicitada) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + qde_solicitada;
		result = prime * result + qde_voucher;
		result = prime * result
				+ ((statusPromocao == null) ? 0 : statusPromocao.hashCode());
		result = prime * result
				+ ((vouchers == null) ? 0 : vouchers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocao other = (Promocao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (qde_solicitada != other.qde_solicitada)
			return false;
		if (qde_voucher != other.qde_voucher)
			return false;
		if (statusPromocao == null) {
			if (other.statusPromocao != null)
				return false;
		} else if (!statusPromocao.equals(other.statusPromocao))
			return false;
		if (vouchers == null) {
			if (other.vouchers != null)
				return false;
		} else if (!vouchers.equals(other.vouchers))
			return false;
		return true;
	}

}
