package org.generation.italy.controller;
import org.generation.italy.repository.DashboardRepository;
import org.generation.italy.service.AcquistoService;
import org.generation.italy.service.DashboardService;
import org.generation.italy.service.PrenotazioneService;
import org.generation.italy.service.ProdottoService;
import org.generation.italy.service.VenditaProdottoService;
import org.generation.italy.service.VenditaService;
import org.generation.italy.service.VisitaService;
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
	
	@Autowired
	private VisitaService visitaService;
	
	@Autowired
	private VenditaProdottoService venditaProdottoService;
	
	@Autowired
	private ProdottoService prodottoService;
	
	@GetMapping

	public String list(Model model) {
		
		model.addAttribute("totalePrenotazioni", prenotazioneService.getTotalePrenotazioni());
		model.addAttribute("totaleVendite", venditaService.getTotaleVendite());
		model.addAttribute("totaleAcquisti", acquistoService.getTotaleAcquisti());
		model.addAttribute("top5", venditaProdottoService.top(5));
		model.addAttribute("inEsaurimento", prodottoService.inEsaurimento());
		model.addAttribute("prodotti", prodottoService.findAllSortedByNome());

		
		model.addAttribute("VisiteProssimoMese", visitaService.getVisiteNextWeek());

		return "/dashboard/dashboard";
	}
	
	
	
	
	
	
	
	
}