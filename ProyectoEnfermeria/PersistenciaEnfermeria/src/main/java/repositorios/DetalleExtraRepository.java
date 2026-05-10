package repositorios;

import entidades.DetalleExtra;
import enums.CategoriaDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface DetalleExtraRepository extends JpaRepository<DetalleExtra, Integer> {

    /**
     * Metodo que sirve para obtener todos los detalles extras que sean antecedentes de un expediente medico
     * @param idExpediente
     * @param categoria
     * @return 
     */
    @Query("SELECT de FROM DetalleExtra de "
            + "JOIN de.detalle d "
            + "WHERE de.expedienteMedico.id = :idExpediente "
            + "AND d.categoria = :categoria")
    public List<DetalleExtra> findByExpedienteYCategoria(Integer idExpediente, CategoriaDetalle categoria);

}
