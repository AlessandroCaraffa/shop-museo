package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.AcquistoProdotto;
import org.generation.italy.model.AcquistoProdottoForm;
import org.generation.italy.repository.AcquistoProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquistoProdottoService {
	@Autowired
	private ProdottoService prodottoService;
	
	@Autowired
	private AcquistoProdottoRepository repository;
	
	@Autowired
	private AcquistoService serviceAcquisto;
	
	public List<AcquistoProdotto> findAll(){
		return repository.findAll();
	}
	
	public AcquistoProdotto save(AcquistoProdottoForm aProdotto, Integer acquistoId) {
		AcquistoProdotto newAProdotto = new AcquistoProdotto();
		newAProdotto.setAcquisto(serviceAcquisto.getById(acquistoId));
		newAProdotto.setPrezzoAcquisto(aProdotto.getPrezzoAcquisto());
		newAProdotto.setQuantita(aProdotto.getQuantita());
		newAProdotto.setProdotto(prodottoService.getById(aProdotto.getProdottoId()));
		

		
		return repository.save(newAProdotto);
	}
	
	public List<AcquistoProdotto> findByProdottoId (Integer prodottoId){
		return repository.findByProdottoId(prodottoId);
	}
	public List<AcquistoProdotto> findByAcquistoId (Integer acquistoId){
		return repository.findByAcquistoId(acquistoId);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public AcquistoProdotto getById(Integer id) {
		return repository.getById(id);
	}
	
	public AcquistoProdotto update(AcquistoProdotto aProdotto) {
		return repository.save(aProdotto);
	}

}
