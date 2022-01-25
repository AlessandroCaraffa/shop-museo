package org.generation.italy.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Percorso {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty(message="Titolo obbligatorio")
	private String titolo;
	
	private String descrizione;
	
	@NotNull
	@Min(value=0)
	private BigDecimal prezzo;
	
	@NotNull
	@Min(value=0)
	private int posti_max;
	
	@OneToMany
	private List<Visita> visite;
	
	@ManyToMany
	private List<Foto> foto;

	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	public int getPosti_max() {
		return posti_max;
	}
	public void setPosti_max(int posti_max) {
		this.posti_max = posti_max;
	}
	public List<Visita> getVisite() {
		return visite;
	}
	public void setVisite(List<Visita> visite) {
		this.visite = visite;
	}
	public List<Foto> getFoto() {
		return foto;
	}
	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	
}
