package org.generation.italy.model;

import javax.validation.constraints.NotNull;

public class VenditaProdottoForm {
	
	private Integer id;
	@NotNull(message="Selezionare prodotto")
	private Prodotto prodotto;
	@NotNull(message="Selezionare quantità")
	private Integer quantita;
	@NotNull
	private Vendita vendita;
	
	//getters e setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	public Vendita getVendita() {
		return vendita;
	}
	public void setVendita(Vendita vendita) {
		this.vendita = vendita;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	

	
	
	
	
	
	

}
