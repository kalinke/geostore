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
@Name("endereco")
@Table(name="GS_ENDERECOS")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_endereco")
	private Long id;
	
	@Column(name="CEP",length=8)
	private String CEP;
	
	@Column(name="numero_logradouro",length=6)
	//@Pattern(regex="^[0-9]{1,40}", message="N˙mero do logradouro inv·lido!")
	private String numeroLogradouro;
	
	@Column(name="logradouro",length=150)
	//@Pattern(regex="^[aA-zZzZ„√ı’Í ‚¬Ù‘Û”˙⁄·¡È…ÌÕ‡¿\\s]+((\\s[aA-zZ„√ı’Í ‚¬Ù‘Û”˙⁄·¡È…ÌÕ‡¿\\s]+)+)?$", message="Nome da rua inv·lido!")
	private String logradouro;
	
	@Column(name="complemento_logradouro",length=30)
	private String complementoLogradouro;
	
	@Column(name="bairro",length=40)
	//@Pattern(regex="^[aA-zZzZ„√ı’Í ‚¬Ù‘Û”˙⁄·¡È…ÌÕ‡¿\\s]+((\\s[aA-zZ„√ı’Í ‚¬Ù‘Û”˙⁄·¡È…ÌÕ‡¿\\s]+)+)?$", message="Nome do bairro inv·lido!")
	private String bairro;
		
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;

	
	@Column(name = "latitude", length=20)
	private Double latitude;
	
	@Column(name = "longitude", length=20)
	private Double longitude;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}

	public void setComplementoLogradouro(String complementoLogradouro) {
		this.complementoLogradouro = complementoLogradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CEP == null) ? 0 : CEP.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime
				* result
				+ ((complementoLogradouro == null) ? 0 : complementoLogradouro
						.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime
				* result
				+ ((numeroLogradouro == null) ? 0 : numeroLogradouro.hashCode());
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
		Endereco other = (Endereco) obj;
		if (CEP == null) {
			if (other.CEP != null)
				return false;
		} else if (!CEP.equals(other.CEP))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (complementoLogradouro == null) {
			if (other.complementoLogradouro != null)
				return false;
		} else if (!complementoLogradouro.equals(other.complementoLogradouro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (numeroLogradouro == null) {
			if (other.numeroLogradouro != null)
				return false;
		} else if (!numeroLogradouro.equals(other.numeroLogradouro))
			return false;
		return true;
	}

	
	


	
	
}
