package org.generation.italy.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    //FOTOFORM
	@NotEmpty(message = "Titolo necessario")
	private String titolo;

	private MultipartFile contenutoPercorso;

	//getter/setter
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
	
	

}
