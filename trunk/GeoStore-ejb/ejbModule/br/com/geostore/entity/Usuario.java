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
@Name("usuario")
@Table(name = "gs_usuarios")

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="nome", length=100)
	private String nome;
		
	@Column(name="senha", length=40)
	private String senha;
		
	@Column(name = "telefone", length=11)
	private String telefone;

	@Column(name = "email", length=90)
	private String email;
	
	@Column(name="cpf", length=11)
	private String cpf;
	
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_usuario")
	private TipoUsuario tipoUsuario;
	
	@ManyToOne
	@JoinColumn(name = "id_status_usuario")
	private StatusUsuario statusUsuario;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa_vinculo")
	private Empresa empresaVinculo;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<Voucher> vouchers;
	
	private int flag;
	
	public Usuario() {
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public Empresa getEmpresaVinculo() {
		return empresaVinculo;
	}
	
	public void setEmpresaVinculo(Empresa empresaVinculo) {
		this.empresaVinculo = empresaVinculo;
	}
	
	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}
	
	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public List<Voucher> getVouchers() {
		return vouchers;
	}


	public void setVouchers(List<Voucher> vouchers) {
		this.vouchers = vouchers;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public int getFlag() {
		return flag;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((empresaVinculo == null) ? 0 : empresaVinculo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result
				+ ((statusUsuario == null) ? 0 : statusUsuario.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result
				+ ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empresaVinculo == null) {
			if (other.empresaVinculo != null)
				return false;
		} else if (!empresaVinculo.equals(other.empresaVinculo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (statusUsuario == null) {
			if (other.statusUsuario != null)
				return false;
		} else if (!statusUsuario.equals(other.statusUsuario))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipoUsuario == null) {
			if (other.tipoUsuario != null)
				return false;
		} else if (!tipoUsuario.equals(other.tipoUsuario))
			return false;
		if (vouchers == null) {
			if (other.vouchers != null)
				return false;
		} else if (!vouchers.equals(other.vouchers))
			return false;
		return true;
	}
	
	
	

}
