package entidades;

import enums.CategoriaDetalle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private CategoriaDetalle categoria;

    @OneToMany(mappedBy = "detalle")
    private Set<DetalleExtra> expedientes;

    public Detalle() {}

    public Detalle(String nombreDetalle, CategoriaDetalle categoria, Set<DetalleExtra> expedientes) {
        this.nombreDetalle = nombreDetalle;
        this.categoria = categoria;
        this.expedientes = expedientes;
    }

    public Detalle(Integer id, String nombreDetalle, CategoriaDetalle categoria, Set<DetalleExtra> expedientes) {
        this.id = id;
        this.nombreDetalle = nombreDetalle;
        this.categoria = categoria;
        this.expedientes = expedientes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDetalle() {
        return nombreDetalle;
    }

    public void setNombreDetalle(String nombreDetalle) {
        this.nombreDetalle = nombreDetalle;
    }

    public CategoriaDetalle getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDetalle categoria) {
        this.categoria = categoria;
    }

    public Set<DetalleExtra> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(Set<DetalleExtra> expedientes) {
        this.expedientes = expedientes;
    }


}