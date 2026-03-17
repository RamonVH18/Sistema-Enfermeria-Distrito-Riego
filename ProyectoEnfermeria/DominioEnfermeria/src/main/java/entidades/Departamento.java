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
import jakarta.persistence.Table;

/**
 *
 * @author Ramon Valencia
 */
@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Integer idDepartamento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Departamento() {
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public Departamento(Integer id, String nombre) {
        this.idDepartamento = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return idDepartamento;
    }

    public void setId(Integer id) {
        this.idDepartamento = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Departamento{"
                + "id=" + idDepartamento
                + ", nombre=" + nombre
                + '}';
    }

}
