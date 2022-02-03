package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.MediaType;
import org.generation.italy.model.AcquistoProdotto;
import org.generation.italy.model.Foto;
import org.generation.italy.model.FotoForm;
import org.generation.italy.model.Guida;
import org.generation.italy.model.Prodotto;
import org.generation.italy.model.ProdottoForm;
import org.generation.italy.model.Vendita;
import org.generation.italy.model.VenditaProdotto;
import org.generation.italy.service.AcquistoProdottoService;
import org.generation.italy.service.AcquistoService;
import org.generation.italy.service.FotoService;
import org.generation.italy.service.ProdottoService;
import org.generation.italy.service.VenditaProdottoService;
import org.generation.italy.service.VenditaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	private FotoService fotoRepo;

	@Autowired
	private AcquistoService acquistoService;
	@Autowired
	private VenditaService venditaService;
	@Autowired
	private VenditaProdottoService venditaPordottoService;
	@Autowired
	private AcquistoProdottoService acquistoProdottoService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("prodotti", service.findAllSortedByNome());
		model.addAttribute("foto", fotoRepo.findAll());
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
	public String doCreate(@Valid @ModelAttribute("prodottoForm") ProdottoForm formProdotto,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors() && formProdotto.getConteuntoProdotto() == null || formProdotto.getConteuntoProdotto().isEmpty()) {

			model.addAttribute("edit", false);
			bindingResult.addError(new ObjectError("content", "Inserire la foto"));
			return "/prodotto/edit";
		}
		
		try {

			service.createProdottoForm(formProdotto);

			redirectAttributes.addFlashAttribute("successMessage", "Prodotto Aggiunto!");
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il Prodotto!");
			e.printStackTrace();
		}

		return "redirect:/prodotto";

	}

	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Prodotto prodotto = service.getById(id);
		Foto foto;

		/*if (prodotto.getQuantitaAcquistata() > 0 || prodotto.getQuantitaVenduta() > 0) {

			for (int i = 0; i < prodotto.getFoto().size(); i++) {
				foto = prodotto.getFoto().get(i);
				prodotto.getFoto().remove(foto);

				fotoRepo.deleteById(foto.getId());
			}

			venditaPordottoService.deleteById(id);
			acquistoProdottoService.deleteById(id);

			service.deleteById(id);
			redirectAttributes.addFlashAttribute("errorMessage", "Il prodotto non può essere eliminato!");
			return "redirect:/prodotto";
		}*/
		

		if((prodotto.getQuantitaAcquistata()-prodotto.getQuantitaVenduta())>0) {
			redirectAttributes.addFlashAttribute("errorMessage", "Il prodotto non può essere eliminato eliminato!");

			return "redirect:/prodotto";
		}
		if((prodotto.getQuantitaAcquistata()-prodotto.getQuantitaVenduta())==0 && prodotto.getQuantitaAcquistata()>0) {
			redirectAttributes.addFlashAttribute("errorMessage", "Il prodotto non può essere eliminato! Esistono delle registrazioni di vendita nella sezione VENDITA");

			return "redirect:/prodotto";
		}
		

			for (int i = 0; i < prodotto.getFoto().size(); i++) {
				foto = prodotto.getFoto().get(i);
				prodotto.getFoto().remove(foto);

				fotoRepo.deleteById(foto.getId());
			}

			service.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "Il prodotto è stato eliminato!");

			return "redirect:/prodotto";
		
	}

	@GetMapping("/{prodottoId}/deleteFoto/{id}")
	public String doDeleteFoto(Model model, @PathVariable("id") Integer id,
			@PathVariable("prodottoId") Integer prodottoId) {
		Prodotto prodotto = service.getById(prodottoId);
		Foto foto = fotoRepo.getById(id);
		prodotto.getFoto().remove(foto);
		service.update(prodotto);

		fotoRepo.deleteById(id);

		return "redirect:/prodotto/editFoto/" + prodottoId;
	}

	@GetMapping("/editProdotto/{id}")
	public String editProdotto(@PathVariable("id") Integer id, Model model) {
		// model.addAttribute("edit", true);
		model.addAttribute("prodotto", service.getById(id));

		return "/prodotto/editProdotto";
	}

	@PostMapping("/editProdotto/{id}")
	public String doUpdateProdotto(@Valid @ModelAttribute("prodotto") Prodotto formProdotto,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {

			return "/prodotto/editProdotto";
		}
		service.update(formProdotto);
		redirectAttributes.addFlashAttribute("successMessage", "Prodotto Aggiornato!");
		return "redirect:/prodotto";
	}

	@GetMapping("/editFoto/{id}")
	public String editFoto(@PathVariable("id") Integer id, Model model) {
		// model.addAttribute("edit", true);
	
		model.addAttribute("prodotto", service.getById(id));
		model.addAttribute("foto", new FotoForm());
		

		return "/prodotto/editFoto";
	}

	@PostMapping("/editFoto/{id}")
	public String doUpdateFoto(@Valid @ModelAttribute("foto") FotoForm formFoto, @PathVariable("id") Integer id,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
	if(bindingResult.hasErrors() && formFoto.getContenuto() == null || formFoto.getContenuto().isEmpty()) {
			
		//redirectAttributes.addFlashAttribute("errorMessage", "Inserire la Foto!");
		bindingResult.addError(new ObjectError("content", "Inserire la foto"));
		return "redirect:/prodotto/editFoto/" + id;
		}

		try {
			Foto addFoto = fotoRepo.create(formFoto);
			Prodotto prodotto = service.getById(id);
			prodotto.getFoto().add(addFoto);
			service.update(prodotto);
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il Prodotto!");
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("successMessage", "Foto Aggiornata!");
		return "redirect:/prodotto";
	}

	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("prodotto", service.getById(id));

		// Prodotto prodottoFotoId=service.getById(id);
		// prodottoFotoId.getFoto();

		// model.addAttribute("fotoList", fotoRepo.findAll());
		return "/prodotto/detail";
	}

	@RequestMapping(value = "/{id}/foto", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getFotoContenuto(@PathVariable Integer id) {
		Foto foto = fotoRepo.getById(id);
		byte[] photoContent = foto.getContenuto();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photoContent, headers, HttpStatus.OK);
	}

}