package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
import org.generation.italy.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/foto")
public class FotoController {
	
	@Autowired
	private FotoService service;

	@GetMapping
	public String fotoList(Model model) {
		model.addAttribute("foto", new FotoForm());
		model.addAttribute("fotoList", service.findAll());
		return "foto";
	}
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("foto") FotoForm foto, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes,
			Model model) {
		// se ci sono errori ritorno la form con gli errori
		if(foto.getContenuto() == null || foto.getContenuto().isEmpty()) {
			bindingResult.addError(new ObjectError("content", "The Photo File is mandatory"));
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("fotoList", service.findAll());
			return "index";
		}
		// se non ci sono errori salvo i dati e redirect alla lista
		try {
			service.create(foto);
			redirectAttributes.addFlashAttribute("successMessage", "Photo added!");
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Unable to save the photo");
			e.printStackTrace();
		}
		return "redirect:/gallery";
		
	}
	
	@RequestMapping(value = "/{id}/foto", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getPhotoContent(@PathVariable Integer id){
		Foto foto = service.getById(id);
		byte[] fotoContenuto = foto.getContenuto();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(fotoContenuto, headers, HttpStatus.OK);
	}
	
}
