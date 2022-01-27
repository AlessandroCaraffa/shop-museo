package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Vendita;
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

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "/vendite/list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("vendite", new Vendita());
		return "/vendite/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("acquisto") Vendita formVendita,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/vendite/edit";
		}
		service.save(formVendita);
		return "redirect:/vendite";
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
