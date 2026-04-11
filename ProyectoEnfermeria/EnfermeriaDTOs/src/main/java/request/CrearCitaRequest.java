package request;

import enums.EstadoCita;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;


/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class CrearCitaRequest {
    // Atributos de la cita
    @NotNull(message = "Fecha y hora requerida.")
    @Future(message = "La fecha y hora debe ser futura.")
    private LocalDateTime fechaHora;
    
    @NotBlank(message = "Motivo requerido.")
    private String motivo;
    
    // Empleado atendido y enfermero encargado
    @NotNull(message = "Empleado requerido.")
    @Positive(message = "Empleado requerido.")
    private Integer idEmpleado;
    
    @NotNull(message = "Enfermero requerido.")
    @Positive(message = "Enfermero requerido.")
    private Integer idEnfermero;
    
    // Constructores
    public CrearCitaRequest() {}

    public CrearCitaRequest(
            LocalDateTime fechaHora, 
            Integer duracionMin, 
            EstadoCita estado, 
            String motivo, 
            Integer idEmpleado, 
            Integer idEnfermero
    ) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.idEmpleado = idEmpleado;
        this.idEnfermero = idEnfermero;
    }

    // Getters
    public LocalDateTime getFechaHora() {return fechaHora;}

    public String getMotivo() {return motivo;}

    public Integer getIdEmpleado() {return idEmpleado;}

    public Integer getIdEnfermero() {return idEnfermero;}
    
    // Setters
    public void setFechaHora(LocalDateTime fechaHora) {this.fechaHora = fechaHora;}

    public void setMotivo(String motivo) {this.motivo = motivo;}

    public void setIdEmpleado(Integer idEmpleado) {this.idEmpleado = idEmpleado;}

    public void setIdEnfermero(Integer idEnfermero) {this.idEnfermero = idEnfermero;}
}