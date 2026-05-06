package repositorios;

import entidades.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
    
    @Query("SELECT e FROM Empleado e WHERE " +
           "LOWER(CONCAT(e.nombres, ' ', e.apellidoPaterno, ' ', e.apellidoMaterno)) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
           "LOWER(e.nombres) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
           "LOWER(e.apellidoPaterno) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
           "LOWER(e.apellidoMaterno) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Empleado> findByNameFilter(@Param("texto") String filtro);
}