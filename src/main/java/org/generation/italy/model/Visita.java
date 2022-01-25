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
	
	private LocalDateTime data_ora_inizio;
	
	private LocalDateTime data_ora_fine;
	
	@ManyToOne
	@JoinColumn(name="id_percorso", nullable=false)
	private Percorso percorso;
	
	@ManyToOne
	@JoinColumn(name="id_guida", nullable=false)
	private Guida guida;

	// getters/setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getData_ora_inizio() {
		return data_ora_inizio;
	}
	public void setData_ora_inizio(LocalDateTime data_ora_inizio) {
		this.data_ora_inizio = data_ora_inizio;
	}
	public LocalDateTime getData_ora_fine() {
		return data_ora_fine;
	}
	public void setData_ora_fine(LocalDateTime data_ora_fine) {
		this.data_ora_fine = data_ora_fine;
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
