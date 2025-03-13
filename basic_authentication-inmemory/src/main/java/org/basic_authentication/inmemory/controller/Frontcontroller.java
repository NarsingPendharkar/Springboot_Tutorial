package org.basic_authentication.inmemory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Frontcontroller {
	
	@GetMapping("/user/user")
	public String userMethod() {
		return "User method";
	}
	
	@GetMapping("/admin/user")
	public String adminMethod() {
		return "admin method";
	}
	
	@GetMapping("/public")
	public String publicMethod() {
		return "public method";
	}
	@GetMapping("/enter")
	public String pcMethod() {
		return "entered method";
	}
	
	@GetMapping("/home")
	public String homeMethod() {
		return "home  method";
	}
	@GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This maps to login.jsp
    }
	

}
