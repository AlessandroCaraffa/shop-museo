package org.generation.italy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.model.Foto;

import org.generation.italy.model.Prodotto;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.repository.FotoRepository;
import org.generation.italy.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoService {
	
	@Autowired
	private ProdottoRepository repository;
	
	@Autowired
	private FotoRepository fotoRepo;
	
	
	public List<Prodotto> findAllSortedByNome(){
		return repository.findAll();
	}
	
	public Prodotto create(Prodotto prodotto) {
		return repository.save(prodotto);
	}
	public Prodotto createProdottoForm(ProdottoForm prodotto) throws IOException {
		Prodotto newProdotto=new Prodotto();
		
		Foto newFotoProdotto= createFoto(prodotto); 
		
		//1-crea una lista vuoto di Foto
		List<Foto> listaFoto=new ArrayList<>();
		//2-aggiungo la mia newFoto alla lista
		listaFoto.add(newFotoProdotto);
		
		//3-set quest lista coem attributo foto del prodotto
		newProdotto.setFoto(listaFoto);
		//4-proseguo a slavare prodotto
		
		
		
		newProdotto.setNome(prodotto.getNome());
		newProdotto.setPrezzoVendita(prodotto.getPrezzoVendita());
		newProdotto.setDescrizione(prodotto.getDescrizione());
		Prodotto prodottoSave=repository.save(newProdotto);
		
		
		
		
		return prodottoSave;
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
	
	
	public Foto createFoto(ProdottoForm newFoto) throws IOException{
		Foto foto = new Foto();
		foto.setTitolo(newFoto.getTitolo());
		if(newFoto.getConteuntoProdotto() != null) {
			byte[] contentSerialized = newFoto.getConteuntoProdotto().getBytes();
			foto.setContenuto(contentSerialized);
		}
		
		return fotoRepo.save(foto);
	};
	
	public List<Foto> findAll(){
		return fotoRepo.findAll();
	}
	

	
	

	
}
