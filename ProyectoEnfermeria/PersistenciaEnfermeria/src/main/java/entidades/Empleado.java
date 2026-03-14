/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author Ramon Valencia
 */
@Entity
@Table(name = "Empleado")
public class Empleado {

    @Id
    @Column
    private Integer id;
    
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

    @Column(name = "estado", nullable = false)
    private String estado;
    
}
