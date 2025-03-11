package com.security.InMemoryAuth.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

public class CustomErrorController implements ErrorController {
	
	@GetMapping("/error")
    public String handleError() {
        return "redirect:/login"; // Redirect all errors to login page
    }

}
