package org.generation.italy.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Visita {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private LocalDateTime dataOraInizio;
	
	@NotNull
	private LocalDateTime dataOraFine;
	
	@ManyToOne
	@JoinColumn(name="percorso_id", nullable=false)
	private Percorso percorso;
	
	@ManyToOne
	private Guida guida;

	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getDataOraInizio() {
		return dataOraInizio;
	}
	public void setDataOraInizio(LocalDateTime dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}
	public LocalDateTime getDataOraFine() {
		return dataOraFine;
	}
	public void setDataOraFine(LocalDateTime dataOraFine) {
		this.dataOraFine = dataOraFine;
	}
	public Percorso getPercorso() {
		return percorso;
	}
	public void setPercorso(Percorso percorso) {
		this.percorso = percorso;
	}
	public Guida getGuida() {
		return guida;
	}
	public void setGuida(Guida guida) {
		this.guida = guida;
	}
	
}
