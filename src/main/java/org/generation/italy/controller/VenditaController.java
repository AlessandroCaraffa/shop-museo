package org.generation.italy.controller;

import java.time.LocalDate;

import javax.validation.Valid;


import org.generation.italy.model.Vendita;
import org.generation.italy.model.VenditaForm;
import org.generation.italy.service.ProdottoService;

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
@RequestMapping("/vendite")
public class VenditaController {
	
	@Autowired
	private VenditaService service;
	
	@Autowired ProdottoService prodottoService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "/vendite/list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		VenditaForm venditaForm = new VenditaForm();
		venditaForm.setDataVendita(LocalDate.now());
		model.addAttribute("vendita", venditaForm);
		return "/vendite/new";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("vendita") VenditaForm formVendita,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/vendite/new";
		}
		Integer venditaId =service.save().getId();
		String url = "redirect:/vendite/" + venditaId.toString();
		return url;
	}
	

	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/vendite";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("vendite", service.getById(id));
		return "/vendite/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("percorso") Vendita formVendita,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			return "/vendite/edit";
		}
		service.update(formVendita);
		return "redirect:/vendite";
	}

}
