package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	


}
