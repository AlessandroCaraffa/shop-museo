package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Acquisto;
import org.generation.italy.repository.AcquistoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquistoService {
	
	@Autowired
	private AcquistoRepository repository;
	
	public List<Acquisto> findAll() {
		return repository.findAll();
	}
	
	public Acquisto save(Acquisto acquisto) {
		return repository.save(acquisto);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Acquisto getById(Integer id) {
		return repository.getById(id);
	}
	
	public Acquisto update(Acquisto acquisto) {
		return repository.save(acquisto);
	}

}
