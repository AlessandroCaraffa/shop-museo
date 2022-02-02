package org.generation.italy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.model.Visita;
import org.generation.italy.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class VisitaService {
	
	@Autowired
	private VisitaRepository repository;
	
	public List<Visita> getVisiteNextMonth(){
		List <Visita> visiteNextmonth = new ArrayList<>();
		List<Visita> visiteTotali = repository.findAll();
		for( Visita visita : visiteTotali) {
			 int i = visita.getData().compareTo(LocalDate.now().plusWeeks(1));
			if(i < 0 ) {
				visiteNextmonth.add(visita);
			}else if( i == 0 ) {
				visiteNextmonth.add(visita);
				
			}
		}
		
		return visiteNextmonth;
	}
	
	public List<Visita> findAll() {
		return repository.findAll();
	}

	public Visita save(Visita visita) {
		return repository.save(visita);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Visita getById(Integer id) {
		return repository.getById(id);
	}
	
	public Visita update(Visita visita) {
		return repository.save(visita);
	}
	
	public List<Visita> findByPercorsoId(Integer id) {
		return repository.findByPercorsoId(id);
	}
	
	public List<Visita> getVisitaNotLessThen2h(@Param("id") Integer id) {
		return repository.getVisitaNotLessThen2h(id);
	}
	
}
