package org.generation.italy.service;

import java.util.List;


import org.generation.italy.model.Vendita;
import org.generation.italy.repository.VenditaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenditaService {
	
	@Autowired
	private VenditaRepository repository;
	
	public List<Vendita> findAll(){
		return repository.findAll();
	}
	public Vendita save(Vendita vendita) {
		return repository.save(vendita);
	}

}
