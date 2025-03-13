package org.basic_authentication.inmemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// authrise
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF for testing (enable in production)
				.authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
						.requestMatchers("/user/**").hasAuthority("ROLE_USER")
						.requestMatchers("/", "/home", "/register").permitAll().anyRequest().authenticated())
				.formLogin(login -> login.loginProcessingUrl("/login") // Default login URL
						.successForwardUrl("/enter").permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll());
		return http.build();
	}

	// authenticate
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		// create user
		// inmemeory authentication
		UserDetails user1 = User.withUsername("narsing").password(passwordEncoder.encode("n123")).roles("USER").build();
		UserDetails admin = User.withUsername("nikita").password(passwordEncoder.encode("n123")).roles("ADMIN").build();
		// return an object of userdetailamanager
		return new InMemoryUserDetailsManager(user1, admin);
	}

	// password encode
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
