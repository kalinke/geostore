package br.com.geostore.entity;

import java.io.Serializable;

public class Buscar implements Serializable{
	private String texto;
	private Double lat;
	private Double log;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLog() {
		return log;
	}
	public void setLog(Double log) {
		this.log = log;
	}
	
}
