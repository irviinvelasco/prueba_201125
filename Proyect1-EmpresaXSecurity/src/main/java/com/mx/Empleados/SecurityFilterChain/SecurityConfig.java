package com.mx.Empleados.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mx.Empleados.RolesUser.RolesUsuario;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		// JWT ----- politicas de autenticacion
		.sessionManagement(session ->
		session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.maximumSessions(1)
		)
		// ----------------------------
		.authorizeHttpRequests(auth ->
		auth.requestMatchers(new AntPathRequestMatcher("/Api/lista")).permitAll()
		.requestMatchers(new AntPathRequestMatcher("/Api/guardar")).permitAll()
		.requestMatchers(new AntPathRequestMatcher("/Api/User/lista")).authenticated()
		.requestMatchers(new AntPathRequestMatcher("/Api/User/login")).permitAll()
		.anyRequest().authenticated()
		)
		.formLogin(login -> login.permitAll())
		.httpBasic()
		.and();
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public final RolesUsuario rolesUsuario;
	
	public SecurityConfig(RolesUsuario rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}
	
	// crear el aministrados de autenticaciones
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(rolesUsuario)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
}
