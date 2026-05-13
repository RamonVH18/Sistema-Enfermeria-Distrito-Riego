package response;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ReagendarCitaResponse {
    
    private String nombre;
    private LocalDateTime fechaActualizacion;

    public ReagendarCitaResponse() {}

    public ReagendarCitaResponse(String nombre, LocalDateTime fechaActualizacion) {
        this.nombre = nombre;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombre() {return nombre;}

    public LocalDateTime getFechaActualizacion() {return fechaActualizacion;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {this.fechaActualizacion = fechaActualizacion;}
}