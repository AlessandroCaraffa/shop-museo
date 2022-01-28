package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
import org.generation.italy.model.Prodotto;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.service.FotoService;
import org.generation.italy.service.ProdottoService;
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
@RequestMapping("/prodotto")
public class ProdottoController {
	
	@Autowired
	private ProdottoService service;
	
	

	@GetMapping
	public String list(Model model) {
		model.addAttribute("prodotti", service.findAllSortedByNome());
		return "/prodotto/list";

	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("prodottoForm", new ProdottoForm());
		model.addAttribute("fotoList", service.findAll());
		return "/prodotto/edit";
	}
 	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("prodottoForm") ProdottoForm formProdotto,BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/prodotto/edit";
		}
		if(formProdotto.getConteuntoProdotto() == null || formProdotto.getConteuntoProdotto().isEmpty()) {
			bindingResult.addError(new ObjectError("content", "The Photo File is mandatory"));
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("fotoList", service.findAll());
			return "/prodotto/edit";
		}try {

			
			service.createProdottoForm(formProdotto);

			redirectAttributes.addFlashAttribute("successMessage", "Prodotto Aggiunto!");
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il Prodotto!");
			e.printStackTrace();
		}
		
		
		return "redirect:/prodotto";

	}
	
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/prodotto";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("prodotto", service.getById(id));
		return "/prodotto/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("prodotto") Prodotto formProdotto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			return "/prodotto/edit";
		}
		service.update(formProdotto);
		return "redirect:/prodotto";
	}
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("prodotto", service.getById(id));
		return "/prodotto/detail";
	}
}