package com.upc.edu.Backend_TravelBazaar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Backend_TravelBazaarStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(Backend_TravelBazaarStoreApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//Posiblemente, esto se cambie por un dominio en específico
				// dominios del front
				registry.addMapping("/**").allowedOrigins("https://travelbaazartf.netlify.app", "http://localhost:4200/").allowedMethods("*").allowedHeaders("*");
			}
		};
	}
}
