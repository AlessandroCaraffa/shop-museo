package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
import org.generation.italy.model.Prodotto;
import org.generation.italy.service.FotoService;
import org.generation.italy.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/prodotto")
public class ProdottoController {
	
	@Autowired
	private ProdottoService service;
	
	@Autowired
	private FotoService fotoService;

	@GetMapping("/magazzino")
	public String list(Model model) {
		model.addAttribute("prodotti", service.findAllSortedByNome());
		return "/prodotto/magazzino";
	}
	
	@GetMapping("/create")
	public String create(@Valid @ModelAttribute("foto") FotoForm foto, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes,Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("prodotto", new Prodotto());
		model.addAttribute("foto",new Foto());
		model.addAttribute("fotoList", fotoService.findAll());
		if(foto.getContenuto() == null || foto.getContenuto().isEmpty()) {
			bindingResult.addError(new ObjectError("content", "The Photo File is mandatory"));
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("fotoList", fotoService.findAll());
			return "/prodotto/edit";
		}try {
			fotoService.create(foto);
			redirectAttributes.addFlashAttribute("successMessage", "Photo added!");
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Unable to save the photo");
			e.printStackTrace();
		}
		return "redirect:/prodotto/create}";
	}
 	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("prodotto") Prodotto formProdotto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/prodotto/edit";
		}
		service.save(formProdotto);
		return "redirect:/prodotto/magazzino";
	}
	//DA CONTROLLARE 
	@RequestMapping(value = "/{id}/foto", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getPhotoContent(@PathVariable Integer id){
		Foto foto = fotoService.getById(id);
		byte[] fotoContenuto = foto.getContenuto();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(fotoContenuto, headers, HttpStatus.OK);
	}
	
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/prodotto/magazzino";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("prodotto", service.getById(id));
		return "/prodotto/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("pizza") Prodotto formProdotto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/prodotto/magazzino";
		}
		service.save(formProdotto);
		return "redirect:/prodotto/magazzino";
	}
	
}