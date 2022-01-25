package org.generation.italy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class VenditaProdotto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Min(value=1)
	private int quantita;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="vendita_id", nullable=false)
	private Vendita vendita;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="prodotto_id", nullable=false)
	private Prodotto prodotto;

	//getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
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
