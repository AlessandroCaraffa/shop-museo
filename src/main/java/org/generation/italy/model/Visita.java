package org.generation.italy.model;

import java.time.LocalDate;
import java.time.LocalTime;

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
	private LocalDate data;
	
	@NotNull
	private LocalTime oraInizio;
	
	@NotNull
	private LocalTime oraFine;
	
	@ManyToOne
	@JoinColumn(name="percorso_id", nullable=false)
	private Percorso percorso;
	
	@NotNull
	@ManyToOne
	private Guida guida;

	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(LocalTime oraInizio) {
		this.oraInizio = oraInizio;
	}
	public LocalTime getOraFine() {
		return oraFine;
	}
	public void setOraFine(LocalTime oraFine) {
		this.oraFine = oraFine;
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
