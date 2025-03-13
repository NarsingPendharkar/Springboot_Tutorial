package org.jwtauth.service;

import java.util.Collections;

import org.jwtauth.entity.Users;
import org.jwtauth.repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Usersservice implements UserDetailsService {

	@Autowired
	private Userrepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users foundUser=userrepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		return new User(foundUser.getUsername(),
				foundUser.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(foundUser.getRole()))
				);
	}

	public void save(Users newUser) {
		userrepository.save(newUser);
	}
	
	

}
