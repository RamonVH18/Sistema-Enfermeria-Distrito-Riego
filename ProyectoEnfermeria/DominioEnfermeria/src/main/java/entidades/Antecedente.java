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
public class Antecedente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antecedente")
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String antecedente;
    
    @Column(nullable = false, length = 100, unique = true)
    private String tipo;
    
    @OneToMany(mappedBy = "antecedente")
    private Set<AntecedenteExpedienteMedico> expedientesMedicos;

    public Antecedente() {}

    public Antecedente(
            String antecedente, 
            String tipo, 
            Set<AntecedenteExpedienteMedico> expedientesMedicos
    ) {
        this.antecedente = antecedente;
        this.tipo = tipo;
        this.expedientesMedicos = expedientesMedicos;
    }

    public Antecedente(
            Integer id, 
            String antecedente, 
            String tipo, 
            Set<AntecedenteExpedienteMedico> expedientesMedicos
    ) {
        this.id = id;
        this.antecedente = antecedente;
        this.tipo = tipo;
        this.expedientesMedicos = expedientesMedicos;
    }
    
    public Integer getId() {return id;}

    public String getAntecedente() {return antecedente;}

    public String getTipo() {return tipo;}

    public Set<AntecedenteExpedienteMedico> getExpedientesMedicos() {return expedientesMedicos;}
    
    public void setId(Integer id) {this.id = id;}

    public void setAntecedente(String antecedente) {this.antecedente = antecedente;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public void setExpedientesMedicos(Set<AntecedenteExpedienteMedico> expedientesMedicos) {this.expedientesMedicos = expedientesMedicos;}
}