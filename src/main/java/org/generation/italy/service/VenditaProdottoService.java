package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Vendita;
import org.generation.italy.model.VenditaForm;
import org.generation.italy.model.VenditaProdotto;
import org.generation.italy.model.VenditaProdottoForm;
import org.generation.italy.repository.VenditaProdottoRepository;
import org.generation.italy.repository.VenditaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenditaProdottoService {
	
	@Autowired
	private VenditaProdottoRepository repository;
	@Autowired
	private VenditaRepository venditaRepository;
	
	public List<VenditaProdotto> findAll(){
		return repository.findAll();
	}
	
	public VenditaProdotto save(VenditaProdottoForm venditaProdottoForm) {
		VenditaProdotto newVenditaProdotto = new VenditaProdotto();
		newVenditaProdotto.setProdotto(venditaProdottoForm.getProdotto());
		newVenditaProdotto.setQuantita(venditaProdottoForm.getQuantita());
		newVenditaProdotto.setVendita(venditaProdottoForm.getVendita());
		
		
		return repository.save(newVenditaProdotto);
	}
	
	

}
