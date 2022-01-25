package org.generation.italy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class AcquistoProdotto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Min(value=1)
	private int quantita;
	
	@NotNull
	@Min(value=0)
	@Column(name="prezzo_acquisto")
	private BigDecimal prezzoAcquisto;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="prodotto_id", nullable=false)
	private Prodotto prodotto;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="acquisto_id", nullable=false)
	private Acquisto acquisto;
	
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

	public BigDecimal getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public void setPrezzoAcquisto(BigDecimal prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public Acquisto getAcquisto() {
		return acquisto;
	}

	public void setAcquisto(Acquisto acquisto) {
		this.acquisto = acquisto;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
}
