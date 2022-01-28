package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.AcquistoProdotto;
import org.generation.italy.service.AcquistoProdottoService;
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
@RequestMapping("/aProdotto")
public class AcquistoProdottoController {
	
	@Autowired
	private AcquistoProdottoService service;

	@PostMapping("/create")
	public String doBorrow(@Valid @ModelAttribute("aProdotto") AcquistoProdotto aProdotto,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "/aProdotto/edit";
		}
		service.save(aProdotto);
		return "redirect:/acquisti/detail/" + aProdotto.getProdotto().getId();
	}
	
	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		AcquistoProdotto acquistoProdotto = service.getById(id);
		model.addAttribute("acquistoProdotto", acquistoProdotto);
		model.addAttribute("edit", true);
		return "/aProdotto/edit";
	}
	
	@PostMapping("{id}/edit")
	public String doEdit(@Valid @ModelAttribute("aProdotto") AcquistoProdotto aProdotto, 
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			return "/aProdotto/edit";
		}
		service.save(aProdotto);
		return "redirect:/acquisti/detail/" + aProdotto.getProdotto().getId();
	}
	
	
	
}
