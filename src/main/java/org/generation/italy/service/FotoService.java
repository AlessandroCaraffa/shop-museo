package org.generation.italy.service;

import java.io.IOException;
import java.util.List;

import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
import org.generation.italy.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository repository;
	
	
	public Foto create(FotoForm newFoto) throws IOException{
		Foto photo = new Foto();
		photo.setTitolo(newFoto.getTitolo());
		if(newFoto.getContenuto() != null) {
			byte[] contentSerialized = newFoto.getContenuto().getBytes();
			photo.setContenuto(contentSerialized);
		}
		
		return repository.save(photo);
	};
	
	public List<Foto> findAllById(Integer id){
		return repository.findAllById(id);
	}
	public List<Foto> findAll(){
		return repository.findAll();
	}
	

	public Foto getById(Integer id) {
		return repository.getById(id);
	}


	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	public Foto update(Foto foto) {
		return repository.save(foto);
	}
}
