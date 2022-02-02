package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Visita;
import org.generation.italy.service.GuidaService;
import org.generation.italy.service.PercorsoService;
import org.generation.italy.service.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/visite")
public class VisitaController {
	
	@Autowired
	private VisitaService service;
	
	@Autowired
	private GuidaService guiService;
	
	@Autowired
	private PercorsoService perService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "/visite/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("visita", service.getById(id));
		return "/visite/detail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("visita", new Visita());
		model.addAttribute("guideList", guiService.findAllSortByCognome());
		model.addAttribute("percorsiList", perService.findAll());
		return "/visite/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("visita") Visita formVisita,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			model.addAttribute("guideList", guiService.findAllSortByCognome());
			model.addAttribute("percorsiList", perService.findAll());
			return "/visite/edit";
		}
		service.save(formVisita);
		redirectAttributes.addFlashAttribute("successMessage", "Visita Aggiunta!");
		return "redirect:/visite";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("visita", service.getById(id));
		model.addAttribute("guideList", guiService.findAllSortByCognome());
		model.addAttribute("percorsiList", perService.findAll());
		return "/visite/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("visita") Visita formVisita,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("guideList", guiService.findAllSortByCognome());
			model.addAttribute("percorsiList", perService.findAll());
			return "/visite/edit";
		}
		service.save(formVisita);
		redirectAttributes.addFlashAttribute("successMessage", "Visita Modificata!");
		return "redirect:/visite";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/visite";
	}
	
}
