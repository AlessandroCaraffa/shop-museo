package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Visita;
import org.generation.italy.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitaService {
	
	@Autowired
	private VisitaRepository repository;
	
	public List<Visita> findAll() {
		return repository.findAll();
	}

}
