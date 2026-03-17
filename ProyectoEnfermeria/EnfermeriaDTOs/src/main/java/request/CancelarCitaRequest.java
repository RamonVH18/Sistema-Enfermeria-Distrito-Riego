package request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class CancelarCitaRequest {
    
    @NotNull
    @Positive
    private Integer idCita;

    public CancelarCitaRequest() {}

    public CancelarCitaRequest(Integer idCita) {this.idCita = idCita;}

    public Integer getIdCita() {return idCita;}

    public void setIdCita(Integer idCita) {this.idCita = idCita;}
}