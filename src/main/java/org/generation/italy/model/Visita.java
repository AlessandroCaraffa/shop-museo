package org.generation.italy.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Visita {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime dataOraInizio;
	
	private LocalDateTime dataOraFine;
	
	@ManyToOne
	@JoinColumn(name="id_percorso", nullable=false)
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
