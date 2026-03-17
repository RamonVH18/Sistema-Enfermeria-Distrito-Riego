package DAOs;

import entidades.Cita;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva - 252390
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>{
    
    Cita findByFechaHora(LocalDateTime fechaHora);
    
    List<Cita> findByEstado(String estado);
    
    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    
}