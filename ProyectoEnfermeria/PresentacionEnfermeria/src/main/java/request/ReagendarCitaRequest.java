package request;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ReagendarCitaRequest {
    
    private Integer idCita;
    private LocalDateTime nuevaFechaHora;

    public ReagendarCitaRequest() {}

    public ReagendarCitaRequest(Integer idCita, LocalDateTime nuevaFechaHora) {
        this.idCita = idCita;
        this.nuevaFechaHora = nuevaFechaHora;
    }

    public Integer getIdCita() {return idCita;}

    public LocalDateTime getNuevaFechaHora() {return nuevaFechaHora;}

    public void setIdCita(Integer idCita) {this.idCita = idCita;}

    public void setNuevaFechaHora(LocalDateTime nuevaFechaHora) {this.nuevaFechaHora = nuevaFechaHora;}
}