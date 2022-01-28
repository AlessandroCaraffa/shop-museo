package org.generation.italy.service;

import java.time.LocalDate;
import java.util.List;


import org.generation.italy.model.Vendita;
import org.generation.italy.model.VenditaForm;
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
	
	public Vendita save() {
		Vendita vendita = new Vendita();
		vendita.setDataVendita(LocalDate.now());
		//totale mancante!!!!
		
		return repository.save(vendita);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Vendita getById(Integer id) {
		return repository.getById(id);
	}
	
	public Vendita update(Vendita vendita) {
		return repository.save(vendita);
	}


}
