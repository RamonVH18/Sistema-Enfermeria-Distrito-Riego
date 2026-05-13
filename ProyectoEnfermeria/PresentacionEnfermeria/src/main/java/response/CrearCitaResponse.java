package response;

import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 */
public class CrearCitaResponse {
    private String nombre;
    private LocalDateTime fechaCita;
    private LocalDateTime fechaCreacion;

    public CrearCitaResponse() {}

    public CrearCitaResponse(String nombre, LocalDateTime fechaCita, LocalDateTime fechaCreacion) {
        this.nombre = nombre;
        this.fechaCita = fechaCita;
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {return nombre;}

    public LocalDateTime getFechaCita() {return fechaCita;}

    public LocalDateTime getFechaCreacion() {return fechaCreacion;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setFechaCita(LocalDateTime fechaCita) {this.fechaCita = fechaCita;}

    public void setFechaCreacion(LocalDateTime fechaCreacion) {this.fechaCreacion = fechaCreacion;}
}