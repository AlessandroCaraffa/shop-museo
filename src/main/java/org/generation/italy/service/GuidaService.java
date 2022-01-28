package org.generation.italy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.model.Foto;
import org.generation.italy.model.Guida;
import org.generation.italy.model.GuidaForm;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.repository.FotoRepository;
import org.generation.italy.repository.GuidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuidaService {
	
	@Autowired
	private GuidaRepository repository;
	@Autowired
	private FotoRepository fotoRepo;
	
	
	public List<Guida> findAllSortByCognome() {
		return repository.findAll();
	}
	
	public Guida save(Guida guida) {
		return repository.save(guida);
	}
	public Guida createGuidaForm(GuidaForm guida) throws IOException {
		Guida newGuida=new Guida();

		Foto newFotoGuida= createFoto(guida); 
		
		//1-crea una lista vuoto di Foto
		List<Foto> listaFoto=new ArrayList<>();
		//2-aggiungo la mia newFoto alla lista
		listaFoto.add(newFotoGuida);
		
		//3-set quest lista coem attributo foto del prodotto
		newGuida.setFoto(newFotoGuida);;
		//4-proseguo a slavare prodotto
		
		
		
		newGuida.setNome(guida.getNome());
		newGuida.setCognome(guida.getCognome());
		newGuida.setBio(guida.getBio());
		Guida guidaSave=repository.save(newGuida);
		
		
		
		return guidaSave;
	}
	
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	
	
	public Guida getById(Integer id) {
		return repository.getById(id);
	}
	
	public Guida update(Guida guida) {
		return repository.save(guida);
	}
	
	public Foto createFoto(GuidaForm newFoto) throws IOException{
		Foto foto = new Foto();
		foto.setTitolo(newFoto.getTitolo());
		if(newFoto.getContenutoGuida() != null) {
			byte[] contentSerialized = newFoto.getContenutoGuida().getBytes();
			foto.setContenuto(contentSerialized);
		}
		
		return fotoRepo.save(foto);
	};
	public List<Foto> findAll(){
		return fotoRepo.findAll();
	}
}
