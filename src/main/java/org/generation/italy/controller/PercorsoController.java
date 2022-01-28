package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.generation.italy.model.Percorso;
import org.generation.italy.model.PercorsoForm;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.service.PercorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/percorsi")
public class PercorsoController {
	
	@Autowired
	private PercorsoService service;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("percorsi", service.findAll());
		return "/percorsi/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("percorso", service.getById(id));
		return "/percorsi/detail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("percorsoForm", new PercorsoForm());
		model.addAttribute("fotoList", service.findAll());
		return "/percorsi/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("percorsoForm") PercorsoForm formPercorso,BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/percorsi/edit";
		}
		if(formPercorso.getContenutoPercorso() == null || formPercorso.getContenutoPercorso().isEmpty()) {
			bindingResult.addError(new ObjectError("content", "The Photo File is mandatory"));
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("fotoList", service.findAll());
			return "/prodotto/edit";
		}try {
			
			service.createPercorsoForm(formPercorso);
			redirectAttributes.addFlashAttribute("successMessage", "Prodotto Aggiunto!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il Prodotto!");
			e.printStackTrace();
		}
		
		
		
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
			model.addAttribute("edit", true);
			return "/percorsi/edit";
		}
		service.update(formPercorso);
		return "redirect:/percorsi";
	}
	
}
