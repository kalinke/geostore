package br.com.geostore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

@Entity
@Name("voucher")
@Table(name = "gs_voucher")
public class Voucher implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="codigo_voucher", length=100)
	private String codigo_voucher;
	
	@Column(name="num_gerado", length=100)
	private int num_gerado; //NUMERO SOLICITADO DO VOUCHER
	
	@ManyToOne
	@JoinColumn(name = "id_promocao")
	private Promocao promocao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo_voucher() {
		return codigo_voucher;
	}

	public void setCodigo_voucher(String codigo_voucher) {
		this.codigo_voucher = codigo_voucher;
	}

	public int getNum_gerado() {
		return num_gerado;
	}

	public void setNum_gerado(int num_gerado) {
		this.num_gerado = num_gerado;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigo_voucher == null) ? 0 : codigo_voucher.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + num_gerado;
		result = prime * result
				+ ((promocao == null) ? 0 : promocao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Voucher other = (Voucher) obj;
		if (codigo_voucher == null) {
			if (other.codigo_voucher != null)
				return false;
		} else if (!codigo_voucher.equals(other.codigo_voucher))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (num_gerado != other.num_gerado)
			return false;
		if (promocao == null) {
			if (other.promocao != null)
				return false;
		} else if (!promocao.equals(other.promocao))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
