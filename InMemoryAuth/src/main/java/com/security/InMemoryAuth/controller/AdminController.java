package com.security.InMemoryAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.InMemoryAuth.Entity.User;
import com.security.InMemoryAuth.Service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	 @GetMapping("/admin")
	 @ResponseBody
	    public String admin() {
	        return "admin";
	    }
	 @PostMapping("/saveUser")
		public String registerUser(@RequestParam String username, @RequestParam String password,
				@RequestParam String role) {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(passwordEncoder.encode(password));
			newUser.setRole(role);
			userService.save(newUser);
			return "redirect:/login";
		}

}
