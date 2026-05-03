package entidades;

import enums.TipoSangre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva
 */
@Entity
@Table(name = "expedientes_medicos")
public class ExpedienteMedico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sangre", nullable = false)
    private TipoSangre tipoSangre;
    
    @Column(name = "numero_seguridad_social", nullable = false, unique = true)
    private Integer numeroSeguridadSocial;
    
    @OneToOne
    @JoinColumn(name = "id_empleado", nullable = false, unique = true)
    private Empleado empleado;
    
    @OneToMany(mappedBy = "expedienteMedico")
    private Set<AntecedenteExpedienteMedico> antecedentes;
    
    @OneToMany(mappedBy = "expedienteMedico")
    private Set<AtributoFisicoExpedienteMedico> atributosFisicos;
    
    @OneToMany(mappedBy = "expedienteMedico")
    private Set<DetalleExtraExpedienteMedico> detallesExtra;
    
    @OneToMany(mappedBy = "expedienteMedico")
    private Set<RegistroMedico> registrosMedicos;

    public ExpedienteMedico() {}

    public ExpedienteMedico(
            TipoSangre tipoSangre, 
            Integer numeroSeguridadSocial, 
            Empleado empleado, 
            Set<AntecedenteExpedienteMedico> antecedentes, 
            Set<AtributoFisicoExpedienteMedico> atributosFisicos, 
            Set<DetalleExtraExpedienteMedico> detallesExtra, 
            Set<RegistroMedico> registrosMedicos
    ) {
        this.tipoSangre = tipoSangre;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.empleado = empleado;
        this.antecedentes = antecedentes;
        this.atributosFisicos = atributosFisicos;
        this.detallesExtra = detallesExtra;
        this.registrosMedicos = registrosMedicos;
    }

    public ExpedienteMedico(
            Integer id, 
            TipoSangre tipoSangre, 
            Integer numeroSeguridadSocial, 
            Empleado empleado, 
            Set<AntecedenteExpedienteMedico> antecedentes, 
            Set<AtributoFisicoExpedienteMedico> atributosFisicos, 
            Set<DetalleExtraExpedienteMedico> detallesExtra, 
            Set<RegistroMedico> registrosMedicos
    ) {
        this.id = id;
        this.tipoSangre = tipoSangre;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.empleado = empleado;
        this.antecedentes = antecedentes;
        this.atributosFisicos = atributosFisicos;
        this.detallesExtra = detallesExtra;
        this.registrosMedicos = registrosMedicos;
    }

    public Integer getId() {return id;}

    public TipoSangre getTipoSangre() {return tipoSangre;}

    public Integer getNumeroSeguridadSocial() {return numeroSeguridadSocial;}

    public Empleado getEmpleado() {return empleado;}

    public Set<AntecedenteExpedienteMedico> getAntecedentes() {return antecedentes;}

    public Set<AtributoFisicoExpedienteMedico> getAtributosFisicos() {return atributosFisicos;}

    public Set<DetalleExtraExpedienteMedico> getDetallesExtra() {return detallesExtra;}

    public Set<RegistroMedico> getRegistrosMedicos() {return registrosMedicos;}

    public void setId(Integer id) {this.id = id;}

    public void setTipoSangre(TipoSangre tipoSangre) {this.tipoSangre = tipoSangre;}

    public void setNumeroSeguridadSocial(Integer numeroSeguridadSocial) {this.numeroSeguridadSocial = numeroSeguridadSocial;}

    public void setEmpleado(Empleado empleado) {this.empleado = empleado;}

    public void setAntecedentes(Set<AntecedenteExpedienteMedico> antecedentes) {this.antecedentes = antecedentes;}

    public void setAtributosFisicos(Set<AtributoFisicoExpedienteMedico> atributosFisicos) {this.atributosFisicos = atributosFisicos;}

    public void setDetallesExtra(Set<DetalleExtraExpedienteMedico> detallesExtra) {this.detallesExtra = detallesExtra;}

    public void setRegistrosMedicos(Set<RegistroMedico> registrosMedicos) {this.registrosMedicos = registrosMedicos;}
}