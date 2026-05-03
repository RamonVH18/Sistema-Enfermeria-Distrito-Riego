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
public class DetalleExtra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer id;
    
    @Column(nullable = false, unique = true, length = 100)
    private String detalle;
    
    @OneToMany(mappedBy = "detalleExtra")
    private Set<DetalleExtraExpedienteMedico> expedientes;

    public DetalleExtra() {}

    public DetalleExtra(String detalle, Set<DetalleExtraExpedienteMedico> expedientes) {
        this.detalle = detalle;
        this.expedientes = expedientes;
    }

    public DetalleExtra(
            Integer id, 
            String detalle, 
            Set<DetalleExtraExpedienteMedico> expedientes
    ) {
        this.id = id;
        this.detalle = detalle;
        this.expedientes = expedientes;
    }

    public Integer getId() {return id;}

    public String getDetalle() {return detalle;}

    public Set<DetalleExtraExpedienteMedico> getExpedientes() {return expedientes;}

    public void setId(Integer id) {this.id = id;}

    public void setDetalle(String detalle) {this.detalle = detalle;}

    public void setExpedientes(Set<DetalleExtraExpedienteMedico> expedientes) {this.expedientes = expedientes;}
}