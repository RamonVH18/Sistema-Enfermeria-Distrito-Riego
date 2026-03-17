/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apiApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Ramon Valencia
 */
@SpringBootApplication(
        scanBasePackages = {
            "controllers",
            "interfaces",
            "servicios",
            "DAOs"
        })
@EnableJpaRepositories(basePackages = "DAOs") // El paquete de tu CitaRepository
@EntityScan(basePackages = "entidades") // El paquete donde están tus @Entity
public class ApiEnfermeriaApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiEnfermeriaApplication.class, args);
    }

}
