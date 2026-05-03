package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Leonardo Flores Leyva
 */
@Entity
public class AtributoFisico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atributo_fisico")
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String atributo;
    
    @Column(nullable = false, length = 100, unique = true)
    private String tipo;
    
    @OneToMany(mappedBy = "atributoFisico")
    private Set<AtributoFisicoExpedienteMedico> expedientesMedicos;

    public AtributoFisico() {}

    public AtributoFisico(
            String atributo, 
            String tipo, 
            Set<AtributoFisicoExpedienteMedico> expedientesMedicos
    ) {
        this.atributo = atributo;
        this.tipo = tipo;
        this.expedientesMedicos = expedientesMedicos;
    }

    public AtributoFisico(
            Integer id, 
            String atributo, 
            String tipo, 
            Set<AtributoFisicoExpedienteMedico> expedientesMedicos
    ) {
        this.id = id;
        this.atributo = atributo;
        this.tipo = tipo;
        this.expedientesMedicos = expedientesMedicos;
    }
    
    public Integer getId() {return id;}

    public String getAtributo() {return atributo;}

    public String getTipo() {return tipo;}

    public Set<AtributoFisicoExpedienteMedico> getExpedientesMedicos() {
        return expedientesMedicos;
    }
    
    public void setId(Integer id) {this.id = id;}

    public void setAtributo(String atributo) {this.atributo = atributo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public void setExpedientesMedicos(Set<AtributoFisicoExpedienteMedico> expedientesMedicos) {
        this.expedientesMedicos = expedientesMedicos;
    }   
}