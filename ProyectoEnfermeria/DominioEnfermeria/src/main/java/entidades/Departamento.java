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
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToOne
    @JoinColumn(name = "id_jefe_departamento")
    private JefeDepartamento jefeDepartamento;

    public Departamento() {
    }

    public Departamento(String nombre, JefeDepartamento jefeDepartamento) {
        this.nombre = nombre;
        this.jefeDepartamento = jefeDepartamento;
    }

    public Departamento(Integer id, String nombre, JefeDepartamento jefeDepartamento) {
        this.id = id;
        this.nombre = nombre;
        this.jefeDepartamento = jefeDepartamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public JefeDepartamento getJefeDepartamento() {
        return jefeDepartamento;
    }

    public void setJefeDepartamento(JefeDepartamento jefeDepartamento) {
        this.jefeDepartamento = jefeDepartamento;
    }

    @Override
    public String toString() {
        return "Departamento{"
                + "id=" + id
                + ", nombre=" + nombre
                + ", jefeDepartamento=" + jefeDepartamento
                + '}';
    }

}
