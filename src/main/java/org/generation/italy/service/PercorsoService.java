package org.generation.italy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.model.Foto;
import org.generation.italy.model.Percorso;
import org.generation.italy.model.PercorsoForm;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.repository.FotoRepository;
import org.generation.italy.repository.PercorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PercorsoService {
	
	@Autowired
	private PercorsoRepository repository;
	
	@Autowired
	private FotoRepository fotoRepo;
	
	public List<Percorso> findAll(){
		return repository.findAll();
	}
	
	public Percorso save(Percorso percorso) {
		return repository.save(percorso);
	}
	
	
	public Percorso createPercorsoForm(PercorsoForm percorso) throws IOException {
		Percorso newPercorso=new Percorso();
		
		Foto newFotoPercorso=createFoto(percorso);
		
		List<Foto> listaFoto=new ArrayList<>();
		
		listaFoto.add(newFotoPercorso);
		
		newPercorso.setFoto(listaFoto);
		
		
		newPercorso.setNomePercorso(percorso.getNomePercorso());
		newPercorso.setDescrizione(percorso.getDescrizione());
		newPercorso.setPrezzo(percorso.getPrezzo());
		newPercorso.setPostiMax(percorso.getPostiMax());
		Percorso percorsoSave=repository.save(newPercorso);
		return percorsoSave;
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Percorso getById(Integer id) {
		return repository.getById(id);
	}
	
	public Percorso update(Percorso percorso) {
		return repository.save(percorso);
	}
	
	

	public Foto createFoto(PercorsoForm newFoto) throws IOException{
		Foto foto = new Foto();
		foto.setTitolo(newFoto.getTitolo());
		if(newFoto.getContenutoPercorso() != null) {
			byte[] contentSerialized = newFoto.getContenutoPercorso().getBytes();
			foto.setContenuto(contentSerialized);
		}
		
		return fotoRepo.save(foto);
	};
	
	
}
