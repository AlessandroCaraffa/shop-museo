package org.generation.italy.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/shop")
public class ShopController {

	@GetMapping
	public String list (Model model) {
		return "/user/shop/shop";
	}
}
