/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoCita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 */
@Entity
@Table(name = "Cita")
public class Cita {
    
    @Id
    @Column
    private Integer id_cita;
    
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fecha_hora;
    
    @Column(name = "estado", nullable = false)
    private EstadoCita estado;
    
    @Column(name = "motivo", nullable = false)
    private String motivo;
    
    @ManyToOne
    @JoinColumn(name = "id_serie")
    private Serie serie;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_enfermero", nullable = false)
    private Enfermero enfermero;

    public Cita() {
    }

    public Cita(Integer id_cita, LocalDateTime fecha_hora, EstadoCita estado, String motivo, Serie serie, Empleado empleado, Enfermero enfermero) {
        this.id_cita = id_cita;
        this.fecha_hora = fecha_hora;
        this.estado = estado;
        this.motivo = motivo;
        this.serie = serie;
        this.empleado = empleado;
        this.enfermero = enfermero;
    }

    public Integer getId_cita() {
        return id_cita;
    }

    public void setId_cita(Integer id_cita) {
        this.id_cita = id_cita;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Enfermero getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(Enfermero enfermero) {
        this.enfermero = enfermero;
    }

    @Override
    public String toString() {
        return "Cita{" + 
                "id_cita=" + id_cita + 
                ", fecha_hora=" + fecha_hora + 
                ", estado=" + estado + 
                ", motivo=" + motivo + 
                ", serie=" + serie + 
                ", empleado=" + empleado + 
                ", enfermero=" + enfermero + 
                '}';
    }
    
    
}
