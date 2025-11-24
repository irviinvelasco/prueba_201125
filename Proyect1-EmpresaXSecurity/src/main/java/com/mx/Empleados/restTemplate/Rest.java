package com.mx.Empleados.restTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Rest {
	
	/* Inyeccion de dependencias
	 * Autowored
	 * Objetos
	 * @Bean
	 * Metodos
	 */
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
