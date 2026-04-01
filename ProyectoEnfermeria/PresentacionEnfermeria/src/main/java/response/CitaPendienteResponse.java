/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 */
public class CitaPendienteResponse {

    private Integer idCita;
    private LocalDateTime fechaHora;
    private String motivo;
    private Integer idEmpleado;
    private String nombreEmpleado;

    public CitaPendienteResponse() {
    }

    public CitaPendienteResponse(Integer idCita, LocalDateTime fechaHora, String motivo, Integer idEmpleado, String nombreEmpleado) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
}
