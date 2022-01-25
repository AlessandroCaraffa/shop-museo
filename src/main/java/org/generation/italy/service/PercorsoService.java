package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Percorso;
import org.generation.italy.repository.PercorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PercorsoService {
	
	@Autowired
	private PercorsoRepository repository;
	
	public List<Percorso> findAll(){
		return repository.findAll();
	}

}
