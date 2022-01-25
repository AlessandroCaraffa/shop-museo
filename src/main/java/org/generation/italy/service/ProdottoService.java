package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Prodotto;

import org.generation.italy.repository.Prodottorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoService {
	@Autowired
	private Prodottorepository prodottoRepository;
	
	public List<Prodotto> findAll(){
		return prodottoRepository.findAll();
	}
}
