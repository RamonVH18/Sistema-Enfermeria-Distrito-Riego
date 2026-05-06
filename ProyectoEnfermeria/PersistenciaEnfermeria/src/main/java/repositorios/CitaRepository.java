package repositorios;

import entidades.Cita;
import enums.EstadoCita;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva - 252390
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>{
    
    Optional<Cita> findByFechaHora(LocalDateTime fechaHora);
    
    List<Cita> findByEstado(EstadoCita estado);
    
    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    
    List<Cita> findByFechaHoraBetweenAndEstado(LocalDateTime inicio, LocalDateTime fin, EstadoCita estado);
    
    @Query("SELECT c FROM Cita c WHERE c.estado = :estado AND (LOWER(CONCAT(c.empleado.nombres, ' ', c.empleado.apellidoPaterno, ' ', c.empleado.apellidoMaterno)) LIKE LOWER(CONCAT('%', :curp_nombre, '%')) OR c.empleado.curp = :curp_nombre)")
    List<Cita> findByNombreOrCurpPendiente(@Param("curp_nombre") String nombreCurp, @Param("estado") EstadoCita estado);
}