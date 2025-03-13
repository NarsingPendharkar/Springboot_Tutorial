package org.dbuser_authentication.controller;

import org.dbuser_authentication.service.Usersservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConf {
	
	@Autowired
	private Usersservice usersservice;	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf((csrf)->csrf.disable())
		.authorizeHttpRequests((auth)->{
			auth.requestMatchers("/admin/**").hasAuthority("ADMIN")
			.requestMatchers("/user/**").hasAuthority("USER")
			.requestMatchers("/login","/logout","/register").permitAll()
			.anyRequest().authenticated();
		}).formLogin(login -> login
        	    .loginPage("/login") 
        	    .loginProcessingUrl("/login")
        	    .defaultSuccessUrl("/dashboard", true)
        	    .permitAll()
        	)
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .permitAll()
        )
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint((request, response, authException) -> {
                response.sendRedirect("/login"); // Redirect to login page instead of /error
            })
        );
		
		return httpSecurity.build();
		
	}
	
	
	// this is optional if we implement userdetailsserce and create bean of password encoder then spring boot automatically 
	// create authentication managaer for authentications
	// when you are using more thatn one authentication like JWT and database then you need to define this bean
	
	  @Bean 
	  public AuthenticationManager authManager() { DaoAuthenticationProvider
	  authenticationProvider=new DaoAuthenticationProvider();
	  authenticationProvider.setUserDetailsService(usersservice);
	  authenticationProvider.setPasswordEncoder(passwordEncoder()); return new
	  ProviderManager(authenticationProvider);
	  
	  }
	 

}
