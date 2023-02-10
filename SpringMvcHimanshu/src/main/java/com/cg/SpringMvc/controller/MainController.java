package com.cg.SpringMvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
//	home page
	@GetMapping("/home")
	public String home(Model model,Authentication auth) {
		model.addAttribute("loginAz",auth.getName());//admin //user
		return "home";
	}

}
