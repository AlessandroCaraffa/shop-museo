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
	private VenditaService venditaService;
	@Autowired
	private ProdottoService prodottoService;
	
	public List<VenditaProdotto> findAll(){
		return repository.findAll();
	}
	
	public VenditaProdotto save(VenditaProdottoForm venditaProdottoForm,Integer venditaId) {
		VenditaProdotto newVenditaProdotto = new VenditaProdotto();
		newVenditaProdotto.setProdotto(prodottoService.getById(venditaProdottoForm.getProdottoId()));
		newVenditaProdotto.setQuantita(venditaProdottoForm.getQuantita());
		newVenditaProdotto.setVendita(venditaService.getById(venditaId));
		
		
		return repository.save(newVenditaProdotto);
	}
	
	public List<VenditaProdotto> findByVenditaId(Integer id) {
		return repository.findByVenditaId(id);
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
