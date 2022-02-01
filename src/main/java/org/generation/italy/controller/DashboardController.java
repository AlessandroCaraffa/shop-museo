package org.generation.italy.controller;
import org.generation.italy.repository.DashboardRepository;
import org.generation.italy.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	private DashboardService service;
	
	private DashboardRepository repository;
	
	@GetMapping
	public String list(Model model) {				
		return "/dashboard/dashboard";
	}
	
	
	
	
	
	
	
	
}