package DAOs;

import entidades.RegistroMedico;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface RegistroMedicoRepository extends JpaRepository<RegistroMedico, Integer> {
    
}