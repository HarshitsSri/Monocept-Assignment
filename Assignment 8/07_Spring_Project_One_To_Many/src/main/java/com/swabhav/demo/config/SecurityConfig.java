package com.swabhav.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.httpBasic(Customizer.withDefaults())

				.authorizeHttpRequests(auth -> auth

						.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

						.requestMatchers(HttpMethod.GET, "/api/departments/**").hasAnyRole("USER", "ADMIN")

						.requestMatchers(HttpMethod.POST, "/api/departments/**").hasRole("ADMIN")

						.requestMatchers(HttpMethod.PUT, "/api/departments/**").hasRole("ADMIN")

						.requestMatchers(HttpMethod.DELETE, "/api/departments/**").hasRole("ADMIN")

						.anyRequest().authenticated());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

		var admin = User.withUsername("admin").password(passwordEncoder.encode("admin123")).roles("ADMIN").build();

		var user = User.withUsername("user").password(passwordEncoder.encode("user123")).roles("USER").build();

		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}