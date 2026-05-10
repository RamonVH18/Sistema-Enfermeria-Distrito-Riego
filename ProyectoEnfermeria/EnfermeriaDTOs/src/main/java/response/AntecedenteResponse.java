/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

/**
 *
 * @author Ramon Valencia
 */
public class AntecedenteResponse {
    
    private String nombreDetalle;
    private String especificacion;

    public AntecedenteResponse() {}

    public AntecedenteResponse(String nombreDetalle, String especificacion) {
        this.nombreDetalle = nombreDetalle;
        this.especificacion = especificacion;
    }

    public String getNombreDetalle() {return nombreDetalle;}

    public void setNombreDetalle(String nombreDetalle) {this.nombreDetalle = nombreDetalle;}

    public String getEspecificacion() {return especificacion;}

    public void setEspecificacion(String especificacion) {this.especificacion = especificacion;}   
}