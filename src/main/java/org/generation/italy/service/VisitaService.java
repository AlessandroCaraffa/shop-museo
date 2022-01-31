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

	public Visita save(Visita visita) {
		return repository.save(visita);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Visita getById(Integer id) {
		return repository.getById(id);
	}
	
	public Visita update(Visita visita) {
		return repository.save(visita);
	}
	
	// TODO work in progress [wip]
	public List<Visita> findByPercorsoId(Integer id) {
		return repository.findByPercorsoId(id);
	}
	
}
