package config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva - 252390
 */
@Configuration
@EntityScan(basePackages = "entidades") 
@EnableJpaRepositories(basePackages = "DAOs")
public class PersistenciaConfig {}