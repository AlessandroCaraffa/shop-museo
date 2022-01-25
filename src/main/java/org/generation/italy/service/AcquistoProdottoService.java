package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.AcquistoProdotto;
import org.generation.italy.repository.AcquistoProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquistoProdottoService {
	
	@Autowired
	private AcquistoProdottoRepository repository;
	
	public List<AcquistoProdotto> findAll(){
		return repository.findAll();
	}

}
