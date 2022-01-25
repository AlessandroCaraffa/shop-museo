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
	private LocalDate data_vendita;
	
	@NotNull
	private BigDecimal totale_vendita;

	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getData_vendita() {
		return data_vendita;
	}
	public void setData_vendita(LocalDate data_vendita) {
		this.data_vendita = data_vendita;
	}
	public BigDecimal getTotale_vendita() {
		return totale_vendita;
	}
	public void setTotale_vendita(BigDecimal totale_vendita) {
		this.totale_vendita = totale_vendita;
	}
	
}
