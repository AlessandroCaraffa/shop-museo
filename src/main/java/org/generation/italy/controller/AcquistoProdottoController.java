package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.AcquistoProdotto;
import org.generation.italy.service.AcquistoProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aProdotto")
public class AcquistoProdottoController {
	
	@Autowired
	private AcquistoProdottoService service;

	@PostMapping("/{id}/aProdotto")
	public String doAcquisto(@Valid @ModelAttribute("aProdotto") AcquistoProdotto aProdotto,
			BindingResult bindingResult, @PathVariable("id") Integer id, Model model) {
		if(bindingResult.hasErrors()) {
			return "/aProdotto/edit";
		}
		service.save(aProdotto, id);
		return "redirect:/acquisti/detail/" + aProdotto.getProdotto().getId();
	}
	
}
