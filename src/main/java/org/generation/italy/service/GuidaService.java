package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Guida;
import org.generation.italy.repository.GuidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuidaService {
	
	@Autowired
	private GuidaRepository repository;
	
	public List<Guida> findAllSortedByNome() {
		return repository.findAll();
	}
	
	public Guida save(Guida guida) {
		return repository.save(guida);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Guida getById(Integer id) {
		return repository.getById(id);
	}
	
	public Guida update(Guida guida) {
		return repository.save(guida);
	}

}
