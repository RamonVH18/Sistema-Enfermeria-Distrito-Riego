/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoEmpleado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ramon Valencia
 */
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "telefono", nullable = false, unique = true)
    private String telefono;

    @Column(name = "curp", nullable = false, unique = true)
    private String curp;

    @Column(name = "unidad_trabajo", nullable = false)
    private String unidadTrabajo;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEmpleado estado;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @OneToOne
    @JoinColumn(name = "id_direccion", nullable = false, unique = true)
    private DireccionEmpleado direccion;

    @OneToMany(mappedBy = "empleado")
    private List<Cita> citas;

    public Empleado() {
    }

    public Empleado(String nombres, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String telefono, String curp, String unidadTrabajo, String genero, EstadoEmpleado estado, Departamento departamento, DireccionEmpleado direccion, List<Cita> citas) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.curp = curp;
        this.unidadTrabajo = unidadTrabajo;
        this.genero = genero;
        this.estado = estado;
        this.departamento = departamento;
        this.direccion = direccion;
        this.citas = citas;
    }

    public Empleado(Integer id, String nombres, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String telefono, String curp, String unidadTrabajo, String genero, EstadoEmpleado estado, Departamento departamento, DireccionEmpleado direccion) {
        this.idEmpleado = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.curp = curp;
        this.unidadTrabajo = unidadTrabajo;
        this.genero = genero;
        this.estado = estado;
        this.departamento = departamento;
        this.direccion = direccion;
    }

    public Integer getId() {
        return idEmpleado;
    }

    public void setId(Integer id) {
        this.idEmpleado = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getUnidadTrabajo() {
        return unidadTrabajo;
    }

    public void setUnidadTrabajo(String unidadTrabajo) {
        this.unidadTrabajo = unidadTrabajo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public EstadoEmpleado getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmpleado estado) {
        this.estado = estado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public DireccionEmpleado getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionEmpleado direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Empleado{"
                + "id=" + idEmpleado
                + ", nombres=" + nombres
                + ", apellidoPaterno=" + apellidoPaterno
                + ", apellidoMaterno=" + apellidoMaterno
                + ", fechaNacimiento=" + fechaNacimiento
                + ", telefono=" + telefono
                + ", curp=" + curp
                + ", unidadTrabajo=" + unidadTrabajo
                + ", genero=" + genero
                + ", estado=" + estado
                + ", departamento=" + departamento
                + ", direccion=" + direccion
                + ", citas=" + citas
                + '}';
    }

}
