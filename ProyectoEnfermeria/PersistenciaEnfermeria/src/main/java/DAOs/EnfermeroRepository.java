package DAOs;

import entidades.Enfermero;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public interface EnfermeroRepository extends JpaRepository<Enfermero, Integer>{
    
    boolean existsByEmpleadoIdEmpleado(Integer idEmpleado);
}