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

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Visita {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime orario;
	
	@ManyToOne
	@JoinColumn(name="percorso_id", nullable=false)
	private Percorso percorso;
	
	@NotNull
	@ManyToOne
	private Guida guida;
	
	@Formula("(SELECT SUM(p.quantita)\r\n"
			+ "FROM prenotazione p\r\n"
			+ "WHERE p.visita_id = id)")
	private Integer postiPrenotati;

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
	public LocalTime getOrario() {
		return orario;
	}
	public void setOrario(LocalTime orario) {
		this.orario = orario;
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
	public Integer getPostiPrenotati() {
		return postiPrenotati;
	}
	public void setPostiPrenotati(Integer postiPrenotati) {
		this.postiPrenotati = postiPrenotati;
	}
	
}
