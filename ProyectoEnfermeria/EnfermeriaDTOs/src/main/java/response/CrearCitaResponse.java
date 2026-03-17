package response;

import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 */
public class CrearCitaResponse {
    
    private String nombre;
    private LocalDateTime fechaCita;

    public CrearCitaResponse() {}

    public CrearCitaResponse(String nombre, LocalDateTime fechaCita) {
        this.nombre = nombre;
        this.fechaCita = fechaCita;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }
}