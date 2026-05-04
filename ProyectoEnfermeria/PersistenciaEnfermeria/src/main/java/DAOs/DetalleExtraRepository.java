package DAOs;

import entidades.DetalleExtra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface DetalleExtraRepository extends JpaRepository<DetalleExtra, Integer> {

    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE d.expedienteMedico.id = :idExpediente")
    public List<DetalleExtra> findByExpediente(Integer idExpediente);

    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE d.expedienteMedico.numeroSeguridadSocial = :numeroSeguridadSocial")
    public List<DetalleExtra> findByNumeroSeguridadSocial(Integer numeroSeguridadSocial);

    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE d.expedienteMedico.empleado.curp = :curp")
    public List<DetalleExtra> findByCurp(String curp);

    @Query("SELECT d FROM DetalleExtraExpedienteMedico d JOIN Usuario u ON d.expedienteMedico.empleado = u.empleado WHERE u.email = :email")
    public List<DetalleExtra> findByEmail(String email);

    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE UPPER(d.valor) = UPPER(:valor)")
    public List<DetalleExtra> findByValor(String valor);

    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE d.detalleExtra.id = :detalleId")
    public List<DetalleExtra> findByDetalleId(@Param("detalleId") Integer detalleId);

    @Query("SELECT d FROM DetalleExtraExpedienteMedico d WHERE UPPER(d.detalleExtra.detalle) = UPPER(:detalle)")
    public List<DetalleExtra> findByDetalle(String detalle);
}
