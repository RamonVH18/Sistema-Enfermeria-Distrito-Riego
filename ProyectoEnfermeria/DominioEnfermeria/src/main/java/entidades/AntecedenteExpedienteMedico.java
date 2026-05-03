package entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 *
 * @author Leonardo Flores Leyva
 */
@Entity
public class AntecedenteExpedienteMedico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antecedente_expediente_medico")
    private Long id;
    
    @Column(length = 500) // No estoy seguro si debería poder ser null
    private String especificacion;
    
    @ManyToOne(cascade = CascadeType.PERSIST) // Nos ahorra el crear el antecedente previamente
    @JoinColumn(name = "id_antecedente", nullable = false)
    private Antecedente antecedente;
    
    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private ExpedienteMedico expedienteMedico;

    public AntecedenteExpedienteMedico() {}

    public AntecedenteExpedienteMedico(
            String especificacion, 
            Antecedente antecedente, 
            ExpedienteMedico expedienteMedico
    ) {
        this.especificacion = especificacion;
        this.antecedente = antecedente;
        this.expedienteMedico = expedienteMedico;
    }

    public AntecedenteExpedienteMedico(
            Long id, 
            String especificacion, 
            Antecedente antecedente, 
            ExpedienteMedico expedienteMedico
    ) {
        this.id = id;
        this.especificacion = especificacion;
        this.antecedente = antecedente;
        this.expedienteMedico = expedienteMedico;
    }
    
    public Long getId() {return id;}

    public String getEspecificacion() {return especificacion;}

    public Antecedente getAntecedente() {return antecedente;}

    public ExpedienteMedico getExpedienteMedico() {return expedienteMedico;}

    public void setId(Long id) {this.id = id;}

    public void setEspecificacion(String especificacion) {this.especificacion = especificacion;}

    public void setAntecedente(Antecedente antecedente) {this.antecedente = antecedente;}

    public void setExpedienteMedico(ExpedienteMedico expedienteMedico) {this.expedienteMedico = expedienteMedico;}
}