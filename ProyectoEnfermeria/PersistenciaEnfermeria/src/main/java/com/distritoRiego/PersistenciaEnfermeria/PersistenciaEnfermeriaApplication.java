package com.distritoRiego.PersistenciaEnfermeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EntityScan(basePackages = "entidades") 
@EnableJpaRepositories(basePackages = "DAOs")
public class PersistenciaEnfermeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenciaEnfermeriaApplication.class, args);
	}

}
