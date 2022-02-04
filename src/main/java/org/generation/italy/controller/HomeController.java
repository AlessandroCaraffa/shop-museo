package org.generation.italy.controller;

import org.generation.italy.service.GuidaService;
import org.generation.italy.service.PercorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private PercorsoService service;
	@Autowired
	private GuidaService guidaService;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("percorsi", service.findAll());
		model.addAttribute("guide", guidaService.findAllSortByCognome());
		return"/home";
	}
}
