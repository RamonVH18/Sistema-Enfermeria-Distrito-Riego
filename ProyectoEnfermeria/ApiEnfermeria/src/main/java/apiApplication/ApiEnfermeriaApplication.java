package apiApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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