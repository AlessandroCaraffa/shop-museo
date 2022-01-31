package org.generation.italy.controller;


import javax.validation.Valid;

import org.generation.italy.model.Acquisto;
import org.generation.italy.model.AcquistoProdotto;
import org.generation.italy.service.AcquistoService;
import org.generation.italy.service.ProdottoService;
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
@RequestMapping("/acquisti")
public class AcquistoController {
	
	@Autowired
	private AcquistoService service;
	
	@Autowired
	private ProdottoService prodottoService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("acquisti", service.findAll());
		return "/acquisti/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("acquisto", service.getById(id));
		return "/acquisti/detail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("acquisto", new Acquisto());
		return "/acquisti/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("acquisto") Acquisto formAcquisto,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/acquisti/edit";
		}
		Integer acquistoId =service.save(formAcquisto).getId();
		String url = "redirect:/acquisti/" + acquistoId.toString();
		return url;
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/acquisti";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("acquisto", service.getById(id));
		return "/acquisti/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("acquisto") Acquisto formAcquisto,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			return "/acquisti/edit";
		}
		service.update(formAcquisto);
		return "redirect:/acquisti";
	}
	
	@GetMapping("/{id}/aProdotto")
	public String aProdotto(@PathVariable("id") Integer id, Model model) {
		AcquistoProdotto aProdotto = new AcquistoProdotto();
		aProdotto.setAcquisto(service.getById(id));
		model.addAttribute("aProdotto", aProdotto);
		model.addAttribute("prodotti", prodottoService.findAllSortedByNome());
		return "/aProdotto/edit";
	}
	
}
