package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Prenotazione;
import org.generation.italy.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository repository;
	
	public List<Prenotazione> findAll() {
		return repository.findAll();
	}

}
