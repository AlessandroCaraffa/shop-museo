package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Prodotto;
import org.generation.italy.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoService {
	
	@Autowired
	private ProdottoRepository repository;
	
	public List<Prodotto> findAllSortedByNome(){
		return repository.findAll();
	}
	
	public Prodotto save(Prodotto prodotto) {
		return repository.save(prodotto);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Prodotto getById(Integer id) {
		return repository.getById(id);
	}
	
	public Prodotto update(Prodotto prodotto) {
		return repository.save(prodotto);
	}

}
