/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package request;

import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 */
public class CrearCitaRequest {

    private LocalDateTime fechaHora;
    
    private String motivo;
    
    private Integer idEmpleado;
    
    private Integer idEnfermero;
    
    // Constructores
    public CrearCitaRequest() {}

    public CrearCitaRequest(
            LocalDateTime fechaHora, 
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
