package org.generation.italy.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class FotoForm {

	private Integer id;
	
	@NotEmpty(message="Titolo necessario")
	private String titolo;
	
	private MultipartFile contenuto;

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
	public MultipartFile getContenuto() {
		return contenuto;
	}
	public void setContenuto(MultipartFile contenuto) {
		this.contenuto = contenuto;
	}

}
