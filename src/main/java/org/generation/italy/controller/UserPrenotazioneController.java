package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Prenotazione;
import org.generation.italy.service.PercorsoService;
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
@RequestMapping("/user/percorsi")
public class UserPrenotazioneController {

	@Autowired
	private PercorsoService percorsoService;
	
	@Autowired
	private VisitaService visitaService;
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	// 1.0 PERCORSO [LISTA]
	// user/percorsi/list
	@GetMapping
	public String percorsiList(Model model) {
		model.addAttribute("percorsi", percorsoService.findAll());
		return "/user/percorsi/list";
	}
	
	// 1.5 PERCORSO [DETTAGLI]
	// user/percorsi/id/detail
	@GetMapping("/{idPer}/detail") // dettagli percorso selzionato per id
	public String percorsiDetail(Model model, @PathVariable("idPer") Integer idPer) {
		model.addAttribute("percorso", percorsoService.getById(idPer));
		return "/user/percorsi/detail";
	}
	
	// 2.0 VISITA [LISTA]
	// user/percorsi/id/visite
	@GetMapping("/{idPer}/visite") // lista delle visite selezionate per id percorso
	public String visiteList(Model model, @PathVariable("idPer") Integer idPer) {
		model.addAttribute("visite", visitaService.getVisitaNotLessThen2h(idPer));
		model.addAttribute("percorso", percorsoService.getById(idPer)); // agg. per titolo
		return "/user/visite/list";
	}
	
	// 2.5 VISITA [DETTAGLI]
	// user/percorsi/id/visite/id/detail
	@GetMapping("/{idPer}/visite/{idVis}/detail") // dettagli visita selezionata per id
	public String visiteDetail(Model model,
			@PathVariable("idPer") Integer idPer,
			@PathVariable("idVis") Integer idVis) {
		model.addAttribute("visita", visitaService.getById(idVis));
		return "/user/visite/detail";
	}
	
	// 3.0 PRENOTAZIONE [CREA]
	// user/percorsi/id/visite/id/prenotazioni/create
	@GetMapping("/{idPer}/visite/{idVis}/prenotazioni/create") // form creazione prenotazioni
	public String create(Model model,
			@PathVariable("idPer") Integer idPer,
			@PathVariable("idVis") Integer idVis) {
		// preparazione form
		Prenotazione p = new Prenotazione(); // nuovo oggetto
		p.setVisita(visitaService.getById(idVis)); // precarico id visita
		model.addAttribute("prenotazione", p); // passa al model
		model.addAttribute("percorso", percorsoService.getById(idPer)); // agg. per titolo, non centra col resto
		model.addAttribute("visita", visitaService.getById(idVis)); // agg. per link action
		return "/user/prenotazioni/edit";
	}
	
	@PostMapping("/{idPer}/visite/{idVis}/prenotazioni/create")
	public String doCreate(Model model, BindingResult bindingResult,
			@Valid @ModelAttribute("prenotazione") Prenotazione formPrenotazione,
			@PathVariable("idPer") Integer idPer,
			@PathVariable("idVis") Integer idVis) {
		if(bindingResult.hasErrors()) {
//			model.addAttribute("percorso", percorsoService.getById(idPer));
//			model.addAttribute("visita", visitaService.getById(idVis));
			return "/user/prenotazioni/edit";
		}
		prenotazioneService.save(formPrenotazione);
		return "redirect:/prenotazioni"; // TODO creare pagina di successo con riepilogo prenotazione appena effettuata
	}
	
}
