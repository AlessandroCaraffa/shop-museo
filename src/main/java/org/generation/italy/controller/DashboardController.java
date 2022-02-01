package org.generation.italy.controller;
import org.generation.italy.repository.DashboardRepository;
import org.generation.italy.service.AcquistoService;
import org.generation.italy.service.DashboardService;
import org.generation.italy.service.PrenotazioneService;
import org.generation.italy.service.VenditaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private VenditaService venditaService;
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private AcquistoService acquistoService;
	
	@GetMapping

	public String list(Model model) {
		
		model.addAttribute("totalePrenotazioni", prenotazioneService.getTotalePrenotazioni());
		model.addAttribute("totaleVendite", venditaService.getTotaleVendite());
		model.addAttribute("totaleAcquisti", acquistoService.getTotaleAcquisti());

		return "/dashboard/dashboard";
	}
	
	
	
	
	
	
	
	
}