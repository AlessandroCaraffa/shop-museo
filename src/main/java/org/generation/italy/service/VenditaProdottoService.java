package org.generation.italy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.generation.italy.model.ProdottoConVendite;
import org.generation.italy.model.VenditaProdotto;
import org.generation.italy.model.VenditaProdottoForm;
import org.generation.italy.repository.VenditaProdottoRepository;
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
	
	public List<ProdottoConVendite> top(int n) {
 	  	List<VenditaProdotto> vendite = findAll();
 	  	
 	  	Map<Integer, Integer> conteggioVendite = new HashMap<>();
 	  	for (VenditaProdotto vendita : vendite) {
 	  		Integer prodottoId = vendita.getProdotto().getId();
 	  		int q = vendita.getQuantita();
 	  		
 	  		LocalDate oneWeekAgo = LocalDate.now().minusMonths(1);
 	  		int compareTo = vendita.getVendita().getDataVendita().compareTo(oneWeekAgo);
 	  		
 	  		if (compareTo < 0) {
 	  			continue;
 	  		}
 	  		
 	  		
	// 	  		if(vendita.getVendita().getDataVendita() >= LocalDate.now() && vendita.getVendita().getDataVendita() <= LocalDate.now()plusWeeks(1))
	 	  	if (!conteggioVendite.containsKey(prodottoId)) {
	 	  		conteggioVendite.put(prodottoId, 0);
	 	  	}
	 	  		
	 	  	conteggioVendite.put(prodottoId, conteggioVendite.get(prodottoId) + q);
 	  	}
 	  	
 	  	List<ProdottoConVendite> result = new ArrayList<>();
 	  	 
 	  	List<Entry<Integer, Integer>> arrayList = 
 	  			new ArrayList<Entry<Integer, Integer>>(conteggioVendite.entrySet());
 	  	
	  	
 	  	arrayList.sort((a, b) -> {
 	  		if (a.getValue() > b.getValue()) {
 	  			return -1;
 	  		}
 	  		
 	  		if (a.getValue() < b.getValue()) {
 	  			return 1;
 	  		}
 	  		
 	  		return 0;
 	  	});
 	  	
 	  	System.out.println(arrayList);
 	  	
 	  	int max = Math.min(n, arrayList.size());
 	  	for (int i = 0; i < max; i++) {
 	  		Entry<Integer, Integer> entry = arrayList.get(i);
 	  		ProdottoConVendite pv = new ProdottoConVendite(prodottoService.getById(entry.getKey()), entry.getValue());
 	  		result.add(pv);
 	  	}
 	  	
 	  	return result;
	}
	
	public VenditaProdotto save(VenditaProdottoForm venditaProdottoForm,Integer venditaId) {
		VenditaProdotto newVenditaProdotto = new VenditaProdotto();
		newVenditaProdotto.setProdotto(prodottoService.getById(venditaProdottoForm.getProdottoId()));
		newVenditaProdotto.setQuantita(venditaProdottoForm.getQuantita());
		newVenditaProdotto.setVendita(venditaService.getById(venditaId));
//		venditaService.getById(venditaId).setTotaleVendita(venditaService.getById(venditaId).getTotaleVendita()  prodottoService.getById(venditaProdottoForm.getProdottoId()).getPrezzoVendita().multiply(BigDecimal.valueOf(venditaProdottoForm.getQuantita())));
		
		return repository.save(newVenditaProdotto);
	}
	
//	public List<VenditaProdottoForm> getTop5(){
//		return repository.getTop5();
//	}
	
	
	public List<VenditaProdotto> findByVenditaId(Integer id) {
		return repository.findByVenditaId(id);
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}
	
	public List<VenditaProdotto> findByProdottoId(Integer id) {
		return repository.findByProdottoId(id);
	}

}
