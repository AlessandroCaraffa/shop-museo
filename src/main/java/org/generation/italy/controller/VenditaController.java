package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Guida;
import org.generation.italy.model.Vendita;
import org.generation.italy.service.ProdottoService;
import org.generation.italy.service.VenditaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendite")
public class VenditaController {
	
	@Autowired
	private VenditaService service;
	
	@Autowired ProdottoService prodottoService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("prodotti", prodottoService.findAllSortedByNome());
		model.addAttribute("guida", new Vendita());
		return "/vendite/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("vendita") Vendita formGuida, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);

			return "/guide/edit";

		}
		service.save(formGuida);
		return "redirect:/guide";
	}
	
}
