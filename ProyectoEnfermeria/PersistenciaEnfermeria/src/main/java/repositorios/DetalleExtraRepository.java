package repositorios;

import entidades.DetalleExtra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface DetalleExtraRepository extends JpaRepository<DetalleExtra, Integer> {

    @Query("SELECT d FROM DetalleExtra d WHERE d.expedienteMedico.id = :idExpediente")
    public List<DetalleExtra> findByExpediente(Integer idExpediente);

}