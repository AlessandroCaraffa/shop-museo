package org.generation.italy.model;

import java.math.BigDecimal;
import java.time.LocalTime;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Percorso {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty(message="Nome Percorso necessario")
	private String nomePercorso;
	
	private String descrizione;
	
	@NotNull
	@Min(value=0)
	private BigDecimal prezzo;
	
	@NotNull
	@Min(value=1)
	private int postiMax;
	
	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime durata;
	
	@OneToMany(mappedBy="percorso")
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
	public String getNomePercorso() {
		return nomePercorso;
	}
	public void setNomePercorso(String nomePercorso) {
		this.nomePercorso = nomePercorso;
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
	public int getPostiMax() {
		return postiMax;
	}
	public void setPostiMax(int postiMax) {
		this.postiMax = postiMax;
	}
	public LocalTime getDurata() {
		return durata;
	}
	public void setDurata(LocalTime durata) {
		this.durata = durata;
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