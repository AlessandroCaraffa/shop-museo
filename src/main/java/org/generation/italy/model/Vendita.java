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
public class Vendita {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private LocalDate dataVendita;
	

	private BigDecimal totaleVendita;
	
	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDataVendita() {
		return dataVendita;
	}
	public void setDataVendita(LocalDate dataVendita) {
		this.dataVendita = dataVendita;
	}
	public BigDecimal getTotaleVendita() {
		return totaleVendita;
	}
	public void setTotaleVendita(BigDecimal totaleVendita) {
		this.totaleVendita = totaleVendita;
	}

}
