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
public class AtributoFisicoExpedienteMedico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atributo_fisico_expediente")
    private Integer id;
    
    @Column(length = 100, nullable = false)
    private String especificacion;
    
    @Column(length = 1000)
    private String nota;
    
    @ManyToOne(cascade = CascadeType.PERSIST) // Nos ahorra el crear el antecedente previamente
    @JoinColumn(name = "id_atributo_fisico", nullable = false)
    private AtributoFisico atributoFisico;
    
    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private ExpedienteMedico expedienteMedico;

    public AtributoFisicoExpedienteMedico() {}

    public AtributoFisicoExpedienteMedico(
            String especificacion, 
            String nota, 
            AtributoFisico atributoFisico, 
            ExpedienteMedico expedienteMedico
    ) {
        this.especificacion = especificacion;
        this.nota = nota;
        this.atributoFisico = atributoFisico;
        this.expedienteMedico = expedienteMedico;
    }

    public AtributoFisicoExpedienteMedico(
            Integer id, 
            String especificacion, 
            String nota, 
            AtributoFisico atributoFisico, 
            ExpedienteMedico expedienteMedico
    ) {
        this.id = id;
        this.especificacion = especificacion;
        this.nota = nota;
        this.atributoFisico = atributoFisico;
        this.expedienteMedico = expedienteMedico;
    }
    
    public Integer getId() {return id;}

    public String getEspecificacion() {return especificacion;}

    public String getNota() {return nota;}

    public AtributoFisico getAtributoFisico() {return atributoFisico;}

    public ExpedienteMedico getExpedienteMedico() {return expedienteMedico;}

    public void setId(Integer id) {this.id = id;}

    public void setEspecificacion(String especificacion) {this.especificacion = especificacion;}

    public void setNota(String nota) {this.nota = nota;}

    public void setAtributoFisico(AtributoFisico atributoFisico) {this.atributoFisico = atributoFisico;}

    public void setExpedienteMedico(ExpedienteMedico expedienteMedico) {this.expedienteMedico = expedienteMedico;}
}