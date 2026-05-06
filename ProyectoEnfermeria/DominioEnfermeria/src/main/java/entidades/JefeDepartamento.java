package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva - 252390
 */
@Entity
@Table(name = "jefes_departamento")
public class JefeDepartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jefe")
    private Integer idJefe;

    @OneToOne
    @JoinColumn(name = "id_empleado", nullable = false, unique = true)
    private Empleado empleado;
    
    @OneToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    public JefeDepartamento() {}

    public JefeDepartamento(Empleado empleado, Departamento departamento) {
        this.empleado = empleado;
        this.departamento = departamento;
    }

    public JefeDepartamento(Integer id, Empleado empleado, Departamento departamento) {
        this.idJefe = id;
        this.empleado = empleado;
        this.departamento = departamento;
    }

    public Integer getId() {return idJefe;}

    public void setId(Integer id) {this.idJefe = id;}

    public Empleado getEmpleado() {return empleado;}

    public void setEmpleado(Empleado empleado) {this.empleado = empleado;}

    public Departamento getDepartamento() {return departamento;}

    public void setDepartamento(Departamento departamento) {this.departamento = departamento;}
}