package response;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class ActualizarCitaResponse {
    
    private String nombre;
    private LocalDateTime fechaActualizacion;

    public ActualizarCitaResponse() {}

    public ActualizarCitaResponse(String nombre, LocalDateTime fechaActualizacion) {
        this.nombre = nombre;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombre() {return nombre;}

    public LocalDateTime getFechaActualizacion() {return fechaActualizacion;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {this.fechaActualizacion = fechaActualizacion;}
}