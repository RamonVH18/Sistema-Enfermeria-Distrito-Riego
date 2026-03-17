package request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class ActualizarCitaRequest {
    
    @NotNull
    @Positive
    private Integer idCita;

    // Atributos de la cita
    @NotNull(message = "Fecha y hora requerida.")
    @Future(message = "La fecha y hora debe ser futura.")
    private LocalDateTime nuevaFechaHora;

    public ActualizarCitaRequest() {}

    public ActualizarCitaRequest(Integer idCita, LocalDateTime nuevaFechaHora) {
        this.idCita = idCita;
        this.nuevaFechaHora = nuevaFechaHora;
    }

    public Integer getIdCita() {return idCita;}

    public LocalDateTime getNuevaFechaHora() {return nuevaFechaHora;}

    public void setIdCita(Integer idCita) {this.idCita = idCita;}

    public void setNuevaFechaHora(LocalDateTime nuevaFechaHora) {this.nuevaFechaHora = nuevaFechaHora;}
}