package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Vendita;
import org.generation.italy.model.VenditaForm;
import org.generation.italy.model.VenditaProdotto;
import org.generation.italy.model.VenditaProdottoForm;
import org.generation.italy.service.ProdottoService;
import org.generation.italy.service.VenditaProdottoService;
import org.generation.italy.service.VenditaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class VenditaProdottoController {
	
	@Autowired
	private VenditaProdottoService service;
	@Autowired
	private ProdottoService prodottoService;
	@Autowired
	private VenditaService venditaService;

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "";
	}
	
	@GetMapping("/vendite/{id}")
	public String create(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("edit", false);
		VenditaProdottoForm venditaProdottoForm = new VenditaProdottoForm();
		venditaProdottoForm.setVendita(venditaService.getById(id));
		model.addAttribute("venditaProdotto", venditaProdottoForm);
		model.addAttribute("prodotti", prodottoService.findAllSortedByNome());
		
		
		
		return "/vendite/edit";
	}
	
	@PostMapping("/vendite/{id}")
	public String doCreate(@Valid @ModelAttribute("vendita") VenditaForm formVendita,
			@Valid @ModelAttribute("venditaId") Integer venditaId,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/vendite/edit";
		}
		model.addAttribute(null, model);

		service.save(formVendita);
		return "redirect:/vendite/create";
	}
	
}