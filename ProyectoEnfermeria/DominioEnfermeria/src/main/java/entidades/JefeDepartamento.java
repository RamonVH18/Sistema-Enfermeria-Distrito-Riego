/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public JefeDepartamento() {
    }

    public JefeDepartamento(Empleado empleado) {
        this.empleado = empleado;
    }

    public JefeDepartamento(Integer id, Empleado empleado) {
        this.idJefe = id;
        this.empleado = empleado;
    }

    public Integer getId() {
        return idJefe;
    }

    public void setId(Integer id) {
        this.idJefe = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "JefeDepartamento{"
                + "id=" + idJefe
                + ", empleado=" + empleado
                + '}';
    }

}
