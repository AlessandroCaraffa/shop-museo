package org.generation.italy.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Acquisto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private LocalDate dataAcquisto;
	
	@NotNull
	@NotEmpty(message="Nome fornitore necessario")
	private String nomeFornitore;
	
	@NotNull
	private BigDecimal totaleAcquisto;
	
	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDataAcquisto() {
		return dataAcquisto;
	}
	public void setDataAcquisto(LocalDate dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}
	public String getNomeFornitore() {
		return nomeFornitore;
	}
	public void setNomeFornitore(String nomeFornitore) {
		this.nomeFornitore = nomeFornitore;
	}
	public BigDecimal getTotaleAcquisto() {
		return totaleAcquisto;
	}
	public void setTotaleAcquisto(BigDecimal totaleAcquisto) {
		this.totaleAcquisto = totaleAcquisto;
	}

}
