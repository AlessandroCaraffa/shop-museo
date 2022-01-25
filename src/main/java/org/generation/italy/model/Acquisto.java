package org.generation.italy.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Acquisto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private LocalDate data_acquisto;
	
	@NotNull
	private String nome_fornitore;
	
	@NotNull
	private BigDecimal totale_acquisto;

	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getData_acquisto() {
		return data_acquisto;
	}
	public void setData_acquisto(LocalDate data_acquisto) {
		this.data_acquisto = data_acquisto;
	}
	public String getNome_fornitore() {
		return nome_fornitore;
	}
	public void setNome_fornitore(String nome_fornitore) {
		this.nome_fornitore = nome_fornitore;
	}
	public BigDecimal getTotale_acquisto() {
		return totale_acquisto;
	}
	public void setTotale_acquisto(BigDecimal totale_acquisto) {
		this.totale_acquisto = totale_acquisto;
	}
	
}
