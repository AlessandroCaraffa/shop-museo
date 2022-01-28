package org.generation.italy.model;

import javax.validation.constraints.NotNull;

public class VenditaProdottoForm {
	
	
	@NotNull(message="Selezionare prodotto")
	private Integer prodottoId;
	@NotNull(message="Selezionare quantità")
	private Integer quantita;

	
	//getters e setters
	
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public Integer getProdottoId() {
		return prodottoId;
	}
	public void setProdottoId(Integer prodottoId) {
		this.prodottoId = prodottoId;
	}
	
	
	
	

	
	
	
	
	
	

}
