package org.generation.italy.model;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class GuidaForm {
	public Integer id;

	@NotNull
	@NotEmpty(message="Nome necessario")
	private String nome;
	
	@NotNull
	@NotEmpty(message="Cognome necessario")
	private String cognome;
	
	@Lob
	private String bio;
	@NotEmpty(message="Titolo necessario")
	private String titolo;
	
	private MultipartFile contenutoGuida;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public MultipartFile getContenutoGuida() {
		return contenutoGuida;
	}

	public void setContenutoGuida(MultipartFile contenutoGuida) {
		this.contenutoGuida = contenutoGuida;
	}
	
	
}
