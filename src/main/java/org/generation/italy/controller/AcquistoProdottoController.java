package org.generation.italy.controller;

import org.generation.italy.service.AcquistoProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AcquistoProdottoController {
	
	@Autowired
	private AcquistoProdottoService service;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "";
	}
	
}
