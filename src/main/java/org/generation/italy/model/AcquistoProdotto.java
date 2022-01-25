package org.generation.italy.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
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
	
	@OneToMany
	@NotNull
	@JoinColumn(name="acquisto_id", nullable=false)
	private List<Acquisto> acquisti;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="prodotto_id", nullable=false)
	private Prodotto prodotto;
	
}
