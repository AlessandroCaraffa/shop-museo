package org.generation.italy.controller;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.generation.italy.service.ProdottoService;


@Controller
@RequestMapping("/shop")
public class ShopController {
    
	@Autowired
	private ProdottoService service;
	
	@GetMapping
	public String list (Model model) {
		model.addAttribute("prodotti", service.findAllSortedByNome());
		return "/user/shop/shop";
	}
}
