package DAOs;

import entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
    
}