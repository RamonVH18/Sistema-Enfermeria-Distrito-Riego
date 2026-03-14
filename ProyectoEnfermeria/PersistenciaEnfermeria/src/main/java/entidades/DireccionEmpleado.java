/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Ramon Valencia
 */
@Entity
@Table(name = "DireccionEmpleado")
public class DireccionEmpleado {
    @Id
    @Column
    private Integer id;
    
    @Column (name = "numero", nullable = false)
    private int numero;
    
    @Column (name = "calle", nullable = false)
    private String calle;
    
    @Column (name = "municipio", nullable = false)
    private String municipio;
    
    @Column (name = "codigo_postal", nullable = false)
    private int cp;
    
    @Column (name = "ciudad", nullable = false)
    private String ciudad;

    public DireccionEmpleado() {
    }

    
    
    
}
