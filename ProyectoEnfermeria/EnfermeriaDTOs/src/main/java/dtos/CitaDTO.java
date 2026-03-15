/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.EstadoCita;
import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 */
public class CitaDTO {

    private Integer idCita;
    private LocalDateTime fechaHora;
    private Integer duracionMin;
    private EstadoCita estado;
    private String motivo;

    // Identificadores de las relaciones
    private Integer idSerie;
    private Integer idEmpleado;
    private Integer idEnfermero;

    public CitaDTO() {
    }

    // Constructor completo para mapeos desde la Entidad
    public CitaDTO(Integer idCita, LocalDateTime fechaHora, Integer duracionMin,
            EstadoCita estado, String motivo, Integer idSerie,
            Integer idEmpleado, Integer idEnfermero) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.duracionMin = duracionMin;
        this.estado = estado;
        this.motivo = motivo;
        this.idSerie = idSerie;
        this.idEmpleado = idEmpleado;
        this.idEnfermero = idEnfermero;
    }

    // Getters y Setters
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

    public Integer getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
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

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEnfermero() {
        return idEnfermero;
    }

    public void setIdEnfermero(Integer idEnfermero) {
        this.idEnfermero = idEnfermero;
    }

    @Override
    public String toString() {
        return "CitaDTO{"
                + "idCita=" + idCita
                + ", fechaHora=" + fechaHora
                + ", duracionMin=" + duracionMin
                + ", estado=" + estado
                + ", motivo='" + motivo + '\''
                + ", idSerie=" + idSerie
                + ", idEmpleado=" + idEmpleado
                + ", idEnfermero=" + idEnfermero
                + '}';
    }

}
