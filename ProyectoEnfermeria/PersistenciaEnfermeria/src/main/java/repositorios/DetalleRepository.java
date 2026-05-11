package repositorios;

import entidades.Detalle;
import enums.CategoriaDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface DetalleRepository extends JpaRepository<Detalle, Integer> {
    
    public List<Detalle> findByCategoria(CategoriaDetalle categoria);
}