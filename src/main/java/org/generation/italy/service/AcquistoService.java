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

}
