package org.generation.italy.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;

@Entity
public class Prodotto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty(message="Nome obbligatorio")
	private String nome;
	
	private String descrizione;
	
	@NotNull
	private BigDecimal prezzoVendita;
	
	@ManyToMany
	private List<Foto> foto;

	@Formula("(select coalesce(sum(ap.quantita),0)\r\n"
			+ "from acquisto_prodotto ap\r\n"
			+ "where ap.prodotto_id = id)")
	private Integer quantitaAcquistata;
	
	@Formula("(select coalesce(sum(vp.quantita),0)\r\n"
			+ "from vendita_prodotto vp\r\n"
			+ "where vp.prodotto_id = id)")
	private Integer quantitaVenduta;
	
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
	public BigDecimal getPrezzoVendita() {
		return prezzoVendita;
	}
	public void setPrezzoVendita(BigDecimal prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	public List<Foto> getFoto() {
		return foto;
	}
	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	public Integer getQuantitaAcquistata() {
		return quantitaAcquistata;
	}
	public Integer getQuantitaVenduta() {
		return quantitaVenduta;
	}

}
