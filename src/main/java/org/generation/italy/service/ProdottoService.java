package org.generation.italy.service;

import java.io.IOException;
import java.util.List;

import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
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
	
	
	public Foto create(ProdottoForm newFoto) throws IOException{
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
