package org.generation.italy.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Prodotto {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty(message="Nome obbligatorio")
	private String nome;
	private String descrizione;
	
	@NotNull
	private BigDecimal prezzo_vendita;
	
	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public BigDecimal getPrezzo_vendita() {
		return prezzo_vendita;
	}
	public void setPrezzo_vendita(BigDecimal prezzo_vendita) {
		this.prezzo_vendita = prezzo_vendita;
	}
	
}
