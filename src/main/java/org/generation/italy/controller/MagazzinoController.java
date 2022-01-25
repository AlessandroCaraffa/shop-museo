package org.generation.italy.controller;

import org.generation.italy.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/magazzino")
public class MagazzinoController {
	
	@Autowired
	private ProdottoService prodottoService;
	
	@GetMapping
	public String magazzino(Model model) {
		model.addAttribute("prodotti", prodottoService.findAll() );
		return"magazzino.html";
	}
}
