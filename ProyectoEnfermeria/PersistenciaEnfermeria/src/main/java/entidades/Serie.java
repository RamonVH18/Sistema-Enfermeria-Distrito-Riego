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
import java.time.LocalTime;

/**
 *
 * @author Ramon Valencia
 */
@Entity
@Table(name = "Serie")
public class Serie {
    
    @Id
    @Column
    private Integer id;
    
    @Column(name = "periodo", nullable = false)
    private String periodo;
    
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;
    
    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    public Serie() {
    }

    public Serie(Integer id, String periodo, LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora) {
        this.id = id;
        this.periodo = periodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Serie{" + 
                "id=" + id + 
                ", periodo=" + periodo + 
                ", fechaInicio=" + fechaInicio + 
                ", fechaFin=" + fechaFin + 
                ", hora=" + hora + 
                '}';
    }
    
    
}
