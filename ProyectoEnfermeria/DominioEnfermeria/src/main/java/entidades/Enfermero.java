package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva - 252390
 */
@Entity
@Table(name = "enfermeros")
public class Enfermero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enfermero")
    private Integer idEnfermero;

    @OneToOne
    @JoinColumn(name = "id_empleado", nullable = false, unique = true)
    private Empleado empleado;

    @OneToMany(mappedBy = "enfermero")
    private List<Cita> citas;

    public Enfermero() {}

    public Enfermero(Empleado empleado, List<Cita> citas) {
        this.empleado = empleado;
        this.citas = citas;
    }

    public Enfermero(Integer id, Empleado empleado, List<Cita> citas) {
        this.idEnfermero = id;
        this.empleado = empleado;
        this.citas = citas;
    }

    public Integer getId() {return idEnfermero;}

    public void setId(Integer id) {this.idEnfermero = id;}

    public Empleado getEmpleado() {return empleado;}

    public void setEmpleado(Empleado empleado) {this.empleado = empleado;}

    public List<Cita> getCitas() {return citas;}

    public void setCitas(List<Cita> citas) {this.citas = citas;}
}