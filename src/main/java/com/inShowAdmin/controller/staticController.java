package com.inShowAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class staticController {
	//@RequestMapping(value = "/inShowAdminLogin", method = RequestMethod.GET)
	@GetMapping("/inShowAdminLogin")
	public String login() {
		return "login";
	}
	//@RequestMapping(value = "/inShowAdmin", method = RequestMethod.GET)  
	@GetMapping("/inShowAdmin")
	public String index() {
		return "index";
	}
}
