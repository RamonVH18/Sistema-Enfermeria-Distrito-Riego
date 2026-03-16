/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoCita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
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

    public Cita(LocalDateTime fechaHora, EstadoCita estado, String motivo, Serie serie, Empleado empleado, Enfermero enfermero) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.motivo = motivo;
        this.serie = serie;
        this.empleado = empleado;
        this.enfermero = enfermero;
    }

    public Cita(Integer idCita, LocalDateTime fechaHora, EstadoCita estado, String motivo, Serie serie, Empleado empleado, Enfermero enfermero) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.motivo = motivo;
        this.serie = serie;
        this.empleado = empleado;
        this.enfermero = enfermero;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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
        return "Cita{"
                + "id_cita=" + idCita
                + ", fecha_hora=" + fechaHora
                + ", estado=" + estado
                + ", motivo=" + motivo
                + ", serie=" + serie
                + ", empleado=" + empleado
                + ", enfermero=" + enfermero
                + '}';
    }

}
