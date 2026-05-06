package repositorios;

import entidades.ExpedienteMedico;
import entidades.RegistroMedico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface RegistroMedicoRepository extends JpaRepository<RegistroMedico, Integer> {
    
    public RegistroMedico findByExpedienteMedico(ExpedienteMedico expediente);
    
    @Query(value = """
        SELECT DISTINCT ON (id_expediente) * 
        FROM registro_medico 
        ORDER BY id_expediente, fecha_creacion DESC, id_registro_medico DESC
        """, nativeQuery = true)
    public List<RegistroMedico> findAllLastRecordEach();
}