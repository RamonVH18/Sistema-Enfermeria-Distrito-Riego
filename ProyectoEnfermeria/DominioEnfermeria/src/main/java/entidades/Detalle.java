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
 * @author Ramon Valencia
 */
@Entity
public class Detalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombreDetalle;

    @OneToMany(mappedBy = "detalle")
    private Set<DetalleExtra> expedientes;

    public Detalle() {}

    public Detalle(String nombreDetalle, Set<DetalleExtra> expedientes) {
        this.nombreDetalle = nombreDetalle;
        this.expedientes = expedientes;
    }

    public Detalle(
            Integer id,
            String nombreDetalle,
            Set<DetalleExtra> expedientes
    ) {
        this.id = id;
        this.nombreDetalle = nombreDetalle;
        this.expedientes = expedientes;
    }

    public Integer getId() {return id;}

    public String getNombreDetalle() {return nombreDetalle;}

    public Set<DetalleExtra> getDetalles() {return expedientes;}

    public void setId(Integer id) {this.id = id;}

    public void setNombreDetalle(String detalle) {this.nombreDetalle = detalle;}

    public void setDetalles(Set<DetalleExtra> expedientes) {this.expedientes = expedientes;}
}