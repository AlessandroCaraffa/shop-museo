package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Prenotazione;
import org.generation.italy.service.PrenotazioneService;
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

@Controller
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService service;
	
	@Autowired
	private VisitaService visitaService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "/prenotazioni/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("prenotazione", service.getById(id));
		return "/prenotazioni/detail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("prenotazione", new Prenotazione());
		model.addAttribute("visiteList", visitaService.findAll()); // TODO selez. non tutte ma a partire da percorso
		return "/prenotazioni/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("prenotazione") Prenotazione formPrenotazione,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			model.addAttribute("visiteList", visitaService.findAll()); // TODO selez. non tutte ma a partire da percorso
			return "/prenotazioni/edit";
		}
		service.save(formPrenotazione);
		return "redirect:/prenotazioni";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("prenotazione", service.getById(id));
		model.addAttribute("visiteList", visitaService.findAll()); // TODO selez. non tutte ma a partire da percorso
		return "/prenotazioni/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("percorso") Prenotazione formPrenotazione,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("visiteList", visitaService.findAll()); // TODO selez. non tutte ma a partire da percorso
			return "/prenotazioni/edit";
		}
		service.update(formPrenotazione);
		return "redirect:/prenotazioni";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/prenotazioni";
	}
	
}