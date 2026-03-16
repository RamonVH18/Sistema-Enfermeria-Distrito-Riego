package request;

import enums.EstadoCita;
import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class CrearCitaRequest {
    // Atributos de la cita
    private LocalDateTime fechaHora;
    private Integer duracionMin;
    private EstadoCita estado;
    private String motivo;
    // Empleado atendido y enfermero encargado
    private Integer idEmpleado;
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
        this.duracionMin = duracionMin;
        this.estado = estado;
        this.motivo = motivo;
        this.idEmpleado = idEmpleado;
        this.idEnfermero = idEnfermero;
    }

    // Getters
    public LocalDateTime getFechaHora() {return fechaHora;}

    public Integer getDuracionMin() {return duracionMin;}

    public EstadoCita getEstado() {return estado;}

    public String getMotivo() {return motivo;}

    public Integer getIdEmpleado() {return idEmpleado;}

    public Integer getIdEnfermero() {return idEnfermero;}
    
    // Setters
    public void setFechaHora(LocalDateTime fechaHora) {this.fechaHora = fechaHora;}

    public void setDuracionMin(Integer duracionMin) {this.duracionMin = duracionMin;}

    public void setEstado(EstadoCita estado) {this.estado = estado;}

    public void setMotivo(String motivo) {this.motivo = motivo;}

    public void setIdEmpleado(Integer idEmpleado) {this.idEmpleado = idEmpleado;}

    public void setIdEnfermero(Integer idEnfermero) {this.idEnfermero = idEnfermero;}
}