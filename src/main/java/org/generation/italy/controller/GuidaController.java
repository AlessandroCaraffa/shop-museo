package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
import org.generation.italy.model.Guida;
import org.generation.italy.model.GuidaForm;
import org.generation.italy.model.Prodotto;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.service.FotoService;
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
	@Autowired
	private FotoService fotoService;

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
			return "/guide/edit";
		}
		try {
			
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
	//
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("edit", true);
		Guida guida=new Guida();
		GuidaForm guidaForm=new GuidaForm();
		guida=service.getById(id);
		guidaForm.setId(guida.getId());
		guidaForm.setNome(guida.getNome());
		guidaForm.setCognome(guida.getCognome());
		guidaForm.setBio(guida.getBio());
		guidaForm.setTitolo(guida.getFoto().getTitolo());
		//guidaForm.setContenutoGuida(guida.getFoto().getContenuto()); da sistemare
		
		
		
		model.addAttribute("guidaForm", guidaForm);
		return "guide/edit";
	}
	//
	@GetMapping("/editGuida/{id}")
	public String editGuida (@PathVariable("id") Integer id, Model model) {
		//model.addAttribute("edit", true);
		model.addAttribute("guida", service.getById(id));
		
		return "/guide/editGuida";
	}
	
	@PostMapping("/editGuida/{id}")
	public String doUpdateGuida(@Valid @ModelAttribute("guida") Guida formGuida, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			
			return "/guide/editGuida";
		}
		service.update(formGuida);
		return "redirect:/guide";
	}
	@GetMapping("/editFoto/{id}")
	public String editFoto (@PathVariable("id") Integer id, Model model) {
		//model.addAttribute("edit", true);
		model.addAttribute("guida", service.getById(id));
		model.addAttribute("foto", fotoService.getById(id));
		model.addAttribute("fotoList", fotoService.findAllById(id));
		
		return "/guide/editFoto";
	}
	
	@PostMapping("/editFoto/{id}")
	public String doUpdateFoto(@Valid @ModelAttribute("foto") Foto formFoto, @PathVariable("id") Integer id,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model) {
if(bindingResult.hasErrors()) {
			
			return "/guide/editGuida";
		}
		fotoService.update(formFoto);
		return "redirect:/guide";
	}
	
}