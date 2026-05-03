package DAOs;

import entidades.DetalleExtraExpedienteMedico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface DetalleExtraExpedienteMedicoRepository extends JpaRepository<DetalleExtraExpedienteMedico, Integer> {
    
    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE d.expedienteMedico.id = :idExpediente")
    public List<DetalleExtraExpedienteMedico> findByExpediente(Integer idExpediente);
    
    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE d.expedienteMedico.numeroSeguridadSocial = :numeroSeguridadSocial")
    public List<DetalleExtraExpedienteMedico> findByNumeroSeguridadSocial(Integer numeroSeguridadSocial);
    
    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE d.expedienteMedico.empleado.curp = :curp")
    public List<DetalleExtraExpedienteMedico> findByCurp(String curp);
    
    @Query("SELECT d FROM DetalleExtraExpedienteMedico d JOIN Usuario u ON d.expedienteMedico.empleado = u.empleado WHERE u.email = :email")
    public List<DetalleExtraExpedienteMedico> findByEmail(String email);
    
    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE UPPER(d.valor) = UPPER(:valor)")
    public List<DetalleExtraExpedienteMedico> findByValor(String valor);
    
    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE d.detalleExtra.id = detalleId")
    public List<DetalleExtraExpedienteMedico> findByDetalleId(Integer detalleId);
    
    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE UPPER(d.detalleExtra.detalle) = UPPER(:detalle)")
    public List<DetalleExtraExpedienteMedico> findByDetalle(String detalle);
}