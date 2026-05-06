package repositorios;

import entidades.ExpedienteMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface ExpedienteMedicoRepository extends JpaRepository<ExpedienteMedico, Integer> {
    
    public ExpedienteMedico findByNumeroSeguridadSocial(Integer numeroSeguridadSocial);
    
    @Query("SELECT e FROM ExpedienteMedico e JOIN Usuario u ON e.empleado = u.empleado WHERE u.email = :email")
    public ExpedienteMedico findByEmail(String email);
    
    @Query("SELECT e FROM ExpedienteMedico e  WHERE e.empleado.curp = :curp")
    public ExpedienteMedico findByCurp(String curp);
    
}