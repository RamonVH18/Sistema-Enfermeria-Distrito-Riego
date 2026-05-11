/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author Ramon Valencia
 */
public class EmpleadoSinExpedienteDTO {

    private Integer idEmpleado;

    private String nombreEmpleado;

    public EmpleadoSinExpedienteDTO() {
    }

    public EmpleadoSinExpedienteDTO(Integer idEmpleado, String nombreEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

}
