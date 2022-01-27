package org.generation.italy.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ProdottoForm {
	
	
	private Integer id;
	
	@NotNull
	@NotEmpty(message="Nome obbligatorio")
	private String nome;
	
	private String descrizione;
	
	@NotNull
	private BigDecimal prezzoVendita;
	
	
	

	@NotEmpty(message="Titolo necessario")
	private String titolo;
	
	private MultipartFile conteuntoProdotto;

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

	public MultipartFile getConteuntoProdotto() {
		return conteuntoProdotto;
	}

	public void setConteuntoProdotto(MultipartFile conteuntoProdotto) {
		this.conteuntoProdotto = conteuntoProdotto;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getPrezzoVendita() {
		return prezzoVendita;
	}

	public void setPrezzoVendita(BigDecimal prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	
	
	
}
