package org.dbuser_authentication.controller;

import org.dbuser_authentication.entity.Users;
import org.dbuser_authentication.service.Usersservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;


@Controller
public class Usercontroller {
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Usersservice userService;
	
	 @GetMapping("/admin")
	 @ResponseBody
	    public String admin() {
	        return "admin";
	    }
	 
	 
	 @GetMapping("/register")
		public String getMethodName() {
			return "register";
		}
		
	 @GetMapping("/dashboard")
		public String dashboard(Model model, HttpSession session) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			model.addAttribute("username", authentication.getName());
			model.addAttribute("role", authentication.getAuthorities().toArray()[0].toString());
			model.addAttribute("loggedIn", true);
			return "dashboard";
		}
	 
	 
	 @GetMapping("/login")
		public String loginPage() {
			return "login";
		}
		
	 
	
	 @PostMapping("/admin/saveUser")
	 @Secured("ROLE_USER") // restirct users to use this method
		public String registerUser(@RequestParam String username, @RequestParam String password,
				@RequestParam String role) {
			Users newUser = new Users();
			newUser.setUsername(username);
			newUser.setPassword(passwordEncoder.encode(password));
			newUser.setRole(role);
			userService.save(newUser);
			return "redirect:/login";
		}

}
