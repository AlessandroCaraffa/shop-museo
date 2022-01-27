package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
import org.generation.italy.model.Prodotto;
import org.generation.italy.service.FotoService;
import org.generation.italy.service.ProdottoService;
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
@RequestMapping("/prodotto")
public class ProdottoController {
	
	@Autowired
	private ProdottoService service;
	
	@Autowired
	private FotoService fotoService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("prodotti", service.findAllSortedByNome());
		return "/prodotto/list";

	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("prodotto", new Prodotto());
		model.addAttribute("foto",new Foto());
		model.addAttribute("fotoList", fotoService.findAll());
		return "/prodotto/edit";
	}
 	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("prodotto") Prodotto formProdotto,@ModelAttribute("foto") FotoForm foto,
			RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/prodotto/edit";
		}
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
		
		service.save(formProdotto);

		return "redirect:/prodotto";

	}
	
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/prodotto";
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
			model.addAttribute("edit", true);
			return "/prodotto/edit";
		}
		service.save(formProdotto);
		return "redirect:/prodotto";
	}
	
}