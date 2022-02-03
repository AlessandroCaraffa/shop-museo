package org.generation.italy.service;

import java.time.LocalDate;
import java.util.List;

import org.generation.italy.model.Acquisto;
import org.generation.italy.repository.AcquistoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AcquistoService {
	
	@Autowired
	private AcquistoRepository repository;
	
	public Integer getTotaleAcquisti() {
		return repository.getTotaleAcquisti();
	}
	
	public List<Acquisto> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.DESC,"id"));
	}
	
	public Acquisto save(Acquisto acquisto) {
		acquisto.setDataAcquisto(LocalDate.now());
		return repository.save(acquisto);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Acquisto getById(Integer id) {
		return repository.getById(id);
	}
	
	public Acquisto update(Acquisto acquisto) {
		acquisto.setDataAcquisto(LocalDate.now());
		return repository.save(acquisto);
	}

}
