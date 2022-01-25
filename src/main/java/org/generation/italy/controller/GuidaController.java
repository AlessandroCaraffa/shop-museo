package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Guida;
import org.generation.italy.service.GuidaService;
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
@RequestMapping("/")
public class GuidaController {
	
	@Autowired
	private GuidaService service;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAllSortedByNome());
		return "";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("guida", new Guida());
		return "/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("guida") Guida formGuida, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/edit";
		}
		service.save(formGuida);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("guida", service.getById(id));
		return "/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("guida") Guida formGuida, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/edit";
		}
		service.save(formGuida);
		return "redirect:/";
	}
	
}
