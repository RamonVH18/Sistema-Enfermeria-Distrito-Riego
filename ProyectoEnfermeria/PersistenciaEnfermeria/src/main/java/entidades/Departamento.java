/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Ramon Valencia
 */
@Entity
@Table(name = "Departamento")
public class Departamento {
    
    @Id
    @Column
    private Integer id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @OneToOne(mappedBy = "departamento")
    private JefeDepartamento jefe;
}
