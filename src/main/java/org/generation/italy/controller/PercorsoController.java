package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Percorso;
import org.generation.italy.service.PercorsoService;
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
@RequestMapping("/percorsi")
public class PercorsoController {
	
	@Autowired
	private PercorsoService service;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "/percorsi/list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("percorso", new Percorso());
		return "percorsi/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("percorso") Percorso formPercorso,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "percorsi/edit";
		}
		service.save(formPercorso);
		return "redirect:/percorsi";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/percorsi";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("percorso", service.getById(id));
		return "/percorsi/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("percorso") Percorso formPercorso,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/percorsi/edit";
		}
		service.update(formPercorso);
		return "redirect:/percorsi";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("percorso", service.getById(id));
		return "/percorsi/detail";
	}
	
}
