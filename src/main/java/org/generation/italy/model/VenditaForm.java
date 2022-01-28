package org.generation.italy.model;

import java.time.LocalDate;

public class VenditaForm {
	private Integer id;
	
	private LocalDate dataVendita;
	private Integer totaleVendita;

	public Integer getTotaleVendita() {
		return totaleVendita;
	}

	public void setTotaleVendita(Integer totaleVendita) {
		this.totaleVendita = totaleVendita;
	}

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
	
	

}
