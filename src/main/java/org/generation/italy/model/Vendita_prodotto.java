package org.generation.italy.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Vendita_prodotto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Min(value=1)
	private int quantita;
	
	@OneToMany
	@NotNull
	@JoinColumn(name="vendita_id", nullable=false)
	private List<Vendita> vendite;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="prodotto_id", nullable=false)
	private Prodotto prodotto;
	
}
