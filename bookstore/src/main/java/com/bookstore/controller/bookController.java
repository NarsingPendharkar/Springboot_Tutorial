package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class bookController {
	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@GetMapping("/auth")
	public String handleAuthRequest(@RequestParam String action) {
		if ("login".equalsIgnoreCase(action)) {
			return "login";
		} else if ("register".equalsIgnoreCase(action)) {
			return "register";
		} else {
			return "index";
		}
	}
}
