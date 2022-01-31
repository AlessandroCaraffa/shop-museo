package org.generation.italy.model;

import java.math.BigDecimal;

public class AcquistoProdottoForm {
	
	private Integer id;
	
	private Integer prodottoId;
	
	private Integer quantita;
	
	private BigDecimal prezzoAcquisto;

	
	//geetters e setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdottoId() {
		return prodottoId;
	}

	public void setProdottoId(Integer prodottoId) {
		this.prodottoId = prodottoId;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public BigDecimal getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public void setPrezzoAcquisto(BigDecimal prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	
	
}
