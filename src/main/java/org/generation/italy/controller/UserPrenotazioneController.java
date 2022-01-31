package org.generation.italy.controller;

import org.generation.italy.service.PercorsoService;
import org.generation.italy.service.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/percorsi") // percorso nella barra degli indirizzi
public class UserPrenotazioneController {

	@Autowired
	private PercorsoService percorsoService;
	
	@Autowired
	private VisitaService visitaService;
	
	@GetMapping // percorso pagina effettivamente visualizzata
	public String percorsiList(Model model) {
		model.addAttribute("percorsi", percorsoService.findAll());
		return "/user/percorsi/list";
	}
	
	@GetMapping("/{id}") // lista delle visite selezionate per id percorso
	public String visiteList(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("visite", visitaService.findByPercorsoId(id));
		return "/user/visite/list";
	}
	
}
