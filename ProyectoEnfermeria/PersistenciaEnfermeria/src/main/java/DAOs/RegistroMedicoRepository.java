package DAOs;

import entidades.ExpedienteMedico;
import entidades.RegistroMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface RegistroMedicoRepository extends JpaRepository<RegistroMedico, Integer> {
    
    
    public RegistroMedico findByExpedienteMedico(ExpedienteMedico expediente);
}