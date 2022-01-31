package org.generation.italy.model;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class PercorsoForm {

	private Integer id;

	// PERCORSO
	@NotNull
	@NotEmpty(message = "Nome Percorso necessario")
	private String nomePercorso;

	private String descrizione;

	@NotNull
	@Min(value = 0)
	private BigDecimal prezzo;

	@NotNull
	@Min(value = 1)
	private int postiMax;

	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime durata;

	// FOTOFORM
	@NotNull
	@NotEmpty(message = "Titolo necessario")
	private String titolo;
	
	@Lob
	@NotNull
	private MultipartFile contenutoPercorso;

	// getter/setter
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

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public MultipartFile getContenutoPercorso() {
		return contenutoPercorso;
	}

	public void setContenutoPercorso(MultipartFile contenutoPercorso) {
		this.contenutoPercorso = contenutoPercorso;
	}

	public LocalTime getDurata() {
		return durata;
	}

	public void setDurata(LocalTime durata) {
		this.durata = durata;
	}

}
