package com.project.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf
	            .ignoringRequestMatchers("/h2-console/**")
	        )

	        .headers(headers -> headers
	            .frameOptions(frame -> frame.disable())
	        )

	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/",
	                "/login",
	                "/users/create",
	                "/users/create/**",
	                "/h2-console/**",
	                "/css/**",
	                "/js/**",
	                "/images/**"
	            ).permitAll()
	            .anyRequest().authenticated()
	        )

	        .formLogin(login -> login
	            .loginPage("/login")
	            .loginProcessingUrl("/login")
	            .defaultSuccessUrl("/", true)
	            .failureUrl("/login?error")
	            .permitAll()
	        )

	        .logout(logout -> logout
	            .logoutSuccessUrl("/")
	        );

	    return http.build();
	}

    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}