package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Foto;
import org.generation.italy.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository repository;
	
	public List<Foto> findAll(){
		return repository.findAll();
	}

}
