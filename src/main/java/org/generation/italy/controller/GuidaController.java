package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.generation.italy.model.Guida;
import org.generation.italy.model.GuidaForm;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.service.GuidaService;
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
@RequestMapping("/guide")
public class GuidaController {
	
	@Autowired
	private GuidaService service;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("guide", service.findAllSortByCognome());
		return "/guide/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("guida", service.getById(id));
		return "/guide/detail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("guidaForm", new GuidaForm());
		
	
		return "/guide/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("guidaForm") GuidaForm formGuida,BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/guide/edit";
		}
		if(formGuida.getContenutoGuida() == null || formGuida.getContenutoGuida().isEmpty()) {
			bindingResult.addError(new ObjectError("content", "The Photo File is mandatory"));
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("fotoList", service.findAll());
			return "/guide/edit";
		}try {
			
			service.createGuidaForm(formGuida);
			redirectAttributes.addFlashAttribute("successMessage", "Dati della Guida Aggiunto!");
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare!");
			e.printStackTrace();
		}
		
		return "redirect:/guide";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/guide";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("guida", service.getById(id));
		return "/guide/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("guida") Guida formGuida, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			return "/guide/edit";
		}
		service.save(formGuida);
		return "redirect:/guide";
	}
	
}
