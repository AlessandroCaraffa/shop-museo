package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
import org.generation.italy.model.Percorso;
import org.generation.italy.model.PercorsoForm;
import org.generation.italy.model.Prodotto;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.service.FotoService;
import org.generation.italy.service.PercorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/percorsi")
public class PercorsoController {
	
	@Autowired
	private PercorsoService service;
	@Autowired
	private FotoService fotoService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("percorsi", service.findAll());
		return "/percorsi/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("percorso", service.getById(id));
		return "/percorsi/detail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("percorsoForm", new PercorsoForm());
		model.addAttribute("fotoList", service.findAll());
		return "/percorsi/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("percorsoForm") PercorsoForm formPercorso,BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/percorsi/edit";
		}
		if(formPercorso.getContenutoPercorso() == null || formPercorso.getContenutoPercorso().isEmpty()) {
			bindingResult.addError(new ObjectError("content", "Inserire la foto"));
			return "/percorsi/edit";
		}
		try {
			
			service.createPercorsoForm(formPercorso);
			redirectAttributes.addFlashAttribute("successMessage", "Percorso Aggiunto!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il Percorso!");
			e.printStackTrace();
		}
		
		
		redirectAttributes.addFlashAttribute("successMessage", "Percorso Aggiunto!");
		return "redirect:/percorsi";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/percorsi";
	}
	@GetMapping("/{percorsoId}/deleteFoto/{id}")
	public String doDeleteFoto(Model model, @PathVariable("id") Integer id,@PathVariable("percorsoId") Integer percorsoId) {
		Percorso percorso=service.getById(percorsoId);
		Foto foto=fotoService.getById(id);
		percorso.getFoto().remove(foto);
		service.update(percorso);
		
		
		
		fotoService.deleteById(id);
		
		return "redirect:/percorsi/editFoto/"+percorsoId;
	}
	
	@GetMapping("/editPercorso/{id}")
	public String editPercorso (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("percorso", service.getById(id));
		
		return "/percorsi/editPercorso";
	}
	
	@PostMapping("/editPercorso/{id}")
	public String doUpdatePercorso(@Valid @ModelAttribute("percorso") Percorso formPercorso, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			
			return "/percorsi/editPercorso";
		}
		service.update(formPercorso);
		redirectAttributes.addFlashAttribute("successMessage", "Percorso Aggiornato!");
		return "redirect:/percorsi";
	}
	@GetMapping("/editFoto/{id}")
	public String editFoto (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("percorso", service.getById(id));
		model.addAttribute("foto", fotoService.getById(id));
		model.addAttribute("fotoList", fotoService.findAllById(id));
		
		return "/percorsi/editFoto";
	}
	
	@PostMapping("/editFoto/{id}")
	public String doUpdateFoto(@Valid @ModelAttribute("foto") FotoForm formFoto, @PathVariable("id") Integer id,BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
	

	try {
	Foto addFoto= fotoService.create(formFoto);
	Percorso percorso=service.getById(id);
	percorso.getFoto().add(addFoto);
	service.update(percorso);
	}
	catch(IOException e){
		
	}
	redirectAttributes.addFlashAttribute("successMessage", "Foto Aggiornata!");
	return "redirect:/percorsi";
}
	
	@RequestMapping(value = "/{id}/foto", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getFotoContenuto(@PathVariable Integer id){
		Foto foto = fotoService.getById(id);
		byte[] photoContent = foto.getContenuto();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photoContent, headers, HttpStatus.OK);
	}
	
}
