package com.mins.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.*;


@Configuration
public class SecurityConfiguration {

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//1. All reqs should be authenticated
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		
		
		//2.If a req is not authenticated, a web page i s shown
		http.httpBasic(withDefaults());
		
		//3.CSRF ->POST,PUT
		http.csrf().disable();
		
		return http.build();
		
		
		
	}
}
