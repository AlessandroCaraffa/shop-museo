package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Prenotazione;
import org.generation.italy.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository repository;
	
	public List<Prenotazione> findAll() {
		return repository.findAll();
	}
	
	public Prenotazione save(Prenotazione prenotazione) {
		return repository.save(prenotazione);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Prenotazione getById(Integer id) {
		return repository.getById(id);
	}
	
	public Prenotazione update(Prenotazione prenotazione) {
		return repository.save(prenotazione);
	}
	
	public Integer getTotalePrenotazioni() {
		return repository.getTotalePrenotazioni();
	}
	
//	public List<Prenotazione> getTotalePrenotazioniById(@Param("id") Integer id) {
//		return repository.getTotalePrenotazioniById(id);
//	}

}
