package DAOs;

import entidades.AntecedenteExpedienteMedico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Leonardo Flores Leyva
 */
public interface AntecedenteExpedienteMedicoRepository extends JpaRepository<AntecedenteExpedienteMedico, Integer> {
    
    @Query("SELECT a FROM AntecedenteExpedienteMedico a WHERE a.expedienteMedico.id = :idExpediente")
    public List<AntecedenteExpedienteMedico> findByExpediente(Integer idExpediente);
    
    @Query("SELECT a FROM AntecedenteExpedienteMedico a WHERE a.expedienteMedico.numeroSeguridadSocial = :numeroSeguridadSocial")
    public List<AntecedenteExpedienteMedico> findByNumeroSeguridadSocial(Integer numeroSeguridadSocial);
    
    @Query("SELECT a FROM AntecedenteExpedienteMedico a WHERE a.expedienteMedico.empleado.curp = :curp")
    public List<AntecedenteExpedienteMedico> findByCurp(String curp);
    
    @Query("SELECT a FROM AntecedenteExpedienteMedico a JOIN Usuario u ON a.expedienteMedico.empleado = u.empleado WHERE u.email = :email")
    public List<AntecedenteExpedienteMedico> findByEmail(String email);
    
    @Query("SELECT a FROM AntecedenteExpedienteMedico a WHERE a.antecedente.id = :idAntecedente")
    public List<AntecedenteExpedienteMedico> findByAntecedenteId(Integer idAntecedente);
    
    @Query("SELECT a FROM AntecedenteExpedienteMedico a WHERE UPPER(a.antecedente.antecedente) = UPPER(:antecedente)")
    public List<AntecedenteExpedienteMedico> findByAntecedente(String antecedente);
    
    @Query("SELECT a FROM AntecedenteExpedienteMedico a WHERE a.antecedente.tipo = :tipo")
    public List<AntecedenteExpedienteMedico> findByTipo(String tipo);
    
    @Query("SELECT a FROM AntecedenteExpedienteMedico a WHERE UPPER(a.antecedente.antecedente) = UPPER(:antecedente) AND a.antecedente.tipo = :tipo")
    public List<AntecedenteExpedienteMedico> findByAntecedenteAndTipo(String antecedente, String tipo);
}