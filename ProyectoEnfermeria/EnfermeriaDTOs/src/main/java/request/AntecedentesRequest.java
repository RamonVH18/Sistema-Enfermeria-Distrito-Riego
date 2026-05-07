/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package request;

/**
 *
 * @author Ramon Valencia
 */
public class AntecedentesRequest {
    
    private Integer idDetalle;
    private String detalle;

    public AntecedentesRequest() {
    }

    public AntecedentesRequest(Integer idDetalle, String detalle) {
        this.idDetalle = idDetalle;
        this.detalle = detalle;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    
}
