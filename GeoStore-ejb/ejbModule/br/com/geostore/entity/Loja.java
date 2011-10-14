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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.Pattern;
import org.jboss.seam.annotations.Name;

@Entity
@Name("loja")
@Table(name = "GS_LOJAS")
public class Loja implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name = "documento",length=14)
	private String documento;

	@Column(name = "razao_social",length=200)
	private String razaoSocial;

	@Column(name = "nome_fantasia",length=200)
	private String nomeFantasia;

	@Column(name = "inscricao_estadual",length=14)
	//@Pattern(regex="^([0-9]{5,14})+$",message="A InscriÁ„o Estadual deve conter apenas n˙meros!")
	private String inscricaoEstadual;
	
	@Column(name = "contato",length=30)
	//@Pattern(regex="^[aA-zZzZ„√ı’Í ‚¬Ù‘Û”˙⁄·¡È…ÌÕ‡¿\\s]+((\\s[aA-zZ„√ı’Í ‚¬Ù‘Û”˙⁄·¡È…ÌÕ‡¿\\s]+)+)?$", message="Nome do contato inv·lido!")
	private String contato;

	@Column(name = "telefone", length=11)
	private String telefone;

	@Column(name = "email", length=90)
	//@Pattern(regex="^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$",message="Digite um e-mail v·lido!")
	//@Pattern(regex="^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+.[a-zA-Z]{2,3})+$",message="Digite um e-mail v·lido!")
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "id_empresa_superior")
	private Empresa empresaSuperior;
	
	@OneToMany(mappedBy="loja", cascade=CascadeType.ALL)
	private List<Produto> produtos;
	
	@ManyToOne
	@JoinColumn(name = "id_status_loja")
	private StatusLoja statusLoja;
	
	public Loja() {
		endereco = new Endereco();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
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
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Empresa getEmpresaSuperior() {
		return empresaSuperior;
	}
	
	public void setEmpresaSuperior(Empresa empresaSuperior) {
		this.empresaSuperior = empresaSuperior;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public StatusLoja getStatusLoja() {
		return statusLoja;
	}
	
	public void setStatusLoja(StatusLoja statusLoja) {
		this.statusLoja = statusLoja;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result
				+ ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((empresaSuperior == null) ? 0 : empresaSuperior.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((inscricaoEstadual == null) ? 0 : inscricaoEstadual
						.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result
				+ ((statusLoja == null) ? 0 : statusLoja.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		Loja other = (Loja) obj;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empresaSuperior == null) {
			if (other.empresaSuperior != null)
				return false;
		} else if (!empresaSuperior.equals(other.empresaSuperior))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inscricaoEstadual == null) {
			if (other.inscricaoEstadual != null)
				return false;
		} else if (!inscricaoEstadual.equals(other.inscricaoEstadual))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (statusLoja == null) {
			if (other.statusLoja != null)
				return false;
		} else if (!statusLoja.equals(other.statusLoja))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	
	
	
}
