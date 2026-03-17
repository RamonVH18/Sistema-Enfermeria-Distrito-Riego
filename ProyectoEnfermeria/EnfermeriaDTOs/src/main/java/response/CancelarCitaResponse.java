package response;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class CancelarCitaResponse {
    
    private String nombreEmpleado;
    private LocalDateTime fechaHoraCancelacion;

    public CancelarCitaResponse() {}

    public CancelarCitaResponse(String nombreEmpleado, LocalDateTime fechaHoraCancelacion) {
        this.nombreEmpleado = nombreEmpleado;
        this.fechaHoraCancelacion = fechaHoraCancelacion;
    }

    public String getNombreEmpleado() {return nombreEmpleado;}

    public LocalDateTime getFechaHoraCancelacion() {return fechaHoraCancelacion;}

    public void setNombreEmpleado(String nombreEmpleado) {this.nombreEmpleado = nombreEmpleado;}

    public void setFechaHoraCancelacion(LocalDateTime fechaHoraCancelacion) {this.fechaHoraCancelacion = fechaHoraCancelacion;}
}