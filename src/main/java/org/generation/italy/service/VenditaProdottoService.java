package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.VenditaProdotto;
import org.generation.italy.repository.VenditaProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenditaProdottoService {
	
	@Autowired
	private VenditaProdottoRepository repository;
	
	public List<VenditaProdotto> findAll(){
		return repository.findAll();
	}

}
