package com.security.InMemoryAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.InMemoryAuth.Entity.User;
import com.security.InMemoryAuth.Service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	  @GetMapping("/saveUser")
	    public String adminDashboard1() {
	    	try {
	    		User user=new User();
	    		user.setUsername("narsing");
	    		user.setPassword("n123");
	    		user.setRole("ADMIN");
	    		userService.saveUser(user);
			} catch (Exception e) {
				System.out.println("error occucred");
			}
	        return "User saved";
	    }  
	  @GetMapping("/data")
		 @ResponseBody
		    public String admin() {
		        return "hello";
		    }
	    

}
