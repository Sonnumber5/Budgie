package com.jackp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/login", "/register/submitRegistration", "/login/submitLogin", "/css/**", "/js/**", "/register", "/styles.css").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	                .loginPage("/login")
	                .loginProcessingUrl("/login/submitLogin")
	                .defaultSuccessUrl("/home", true)
	                .failureUrl("/login?error")
	                .permitAll()
	            );

	    return http.build();
	}
}
