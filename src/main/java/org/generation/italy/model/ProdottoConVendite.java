package org.generation.italy.model;

public class ProdottoConVendite {
	private Prodotto prodotto;
	
	private int numeroVendite;

	public ProdottoConVendite(Prodotto prodotto, int numeroVendite) {
		this.prodotto = prodotto;
		this.numeroVendite = numeroVendite;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public int getNumeroVendite() {
		return numeroVendite;
	}

	public void setNumeroVendite(int numeroVendite) {
		this.numeroVendite = numeroVendite;
	}
	
	
}
