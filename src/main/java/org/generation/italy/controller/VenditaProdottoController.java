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
	public String create(@PathVariable("id") Integer venditaId,Model model) {
		model.addAttribute("edit", false);
		VenditaProdottoForm venditaProdottoForm = new VenditaProdottoForm();
		
		model.addAttribute("venditaProdotto", venditaProdottoForm);
		model.addAttribute("prodotti", prodottoService.findAllSortedByNome());
		model.addAttribute("venditaId", venditaId);
		model.addAttribute("venditaProdotti", service.findByVenditaId(venditaId));
		
		
		return "/vendite/edit";
	}
	
	@PostMapping("/vendite/{id}")
	public String doCreate(@Valid @ModelAttribute("venditaProdotto") VenditaProdottoForm formVendita,
			BindingResult bindingResult, Model model,
			@PathVariable("id") Integer venditaId
			) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("venditaProdotti", service.findByVenditaId(venditaId));
			model.addAttribute("prodotti", prodottoService.findAllSortedByNome());
			model.addAttribute("venditaId", venditaId);
			return "/vendite/edit";
		}
		
		model.addAttribute("venditaId", venditaId);
		
		service.save(formVendita,venditaId);
		String url = "redirect:/vendite/" + venditaId.toString();
		return url;
	}
	@GetMapping("vendite/delete/{venditaId}/{venditaProdottoId}")
	public String doDelete(Model model, @PathVariable("venditaId") Integer venditaId,@PathVariable("venditaProdottoId") Integer venditaProdottoId) {
		service.deleteById(venditaProdottoId);
		return "redirect:/vendite/{venditaId}";

	}
	
}