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
	
	public Percorso save(Percorso percorso) {
		return repository.save(percorso);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Percorso getById(Integer id) {
		return repository.getById(id);
	}
	
	public Percorso update(Percorso percorso) {
		return repository.save(percorso);
	}

}
