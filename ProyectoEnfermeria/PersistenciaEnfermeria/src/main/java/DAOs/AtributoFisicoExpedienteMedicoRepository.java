package DAOs;

import entidades.AtributoFisicoExpedienteMedico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface AtributoFisicoExpedienteMedicoRepository extends JpaRepository<AtributoFisicoExpedienteMedico, Integer> {
    
    @Query("SELECT a FROM AtributoFisicoExpedienteMedico a WHERE a.expedienteMedico.id = :idExpediente")
    public List<AtributoFisicoExpedienteMedico> findByExpediente(Integer idExpediente);
    
    @Query("SELECT a FROM AtributoFisicoExpedienteMedico a WHERE a.expedienteMedico.numeroSeguridadSocial = :numeroSeguridadSocial")
    public List<AtributoFisicoExpedienteMedico> findByNumeroSeguridadSocial(Integer numeroSeguridadSocial);
    
    @Query("SELECT a FROM AtributoFisicoExpedienteMedico a WHERE a.expedienteMedico.empleado.curp = :curp")
    public List<AtributoFisicoExpedienteMedico> findByCurp(String curp);
    
    @Query("SELECT a FROM AtributoFisicoExpedienteMedico a JOIN Usuario u ON a.expedienteMedico.empleado = u.empleado WHERE u.email = :email")
    public List<AtributoFisicoExpedienteMedico> findByEmail(String email);
    
    @Query("SELECT a FROM AtributoFisicoExpedienteMedico a WHERE a.atributoFisico.id = :idAtributo")
    public List<AtributoFisicoExpedienteMedico> findByAtributoId(Integer idAtributo);
    
    @Query("SELECT a FROM AtributoFisicoExpedienteMedico a WHERE UPPER(a.atributoFisico.atributo) = UPPER(:atributo)")
    public List<AtributoFisicoExpedienteMedico> findByAtributo(String atributo);
    
    @Query("SELECT a FROM AtributoFisicoExpedienteMedico a WHERE a.atributoFisico.tipo = :tipo")
    public List<AtributoFisicoExpedienteMedico> findByTipo(String tipo);
    
    @Query("SELECT a FROM AtributoFisicoExpedienteMedico a WHERE UPPER(a.atributoFisico.atributo) = UPPER(:atributo) AND a.atributoFisico.tipo = :tipo")
    public List<AtributoFisicoExpedienteMedico> findByAtributoAndTipo(String atributo, String tipo);
}