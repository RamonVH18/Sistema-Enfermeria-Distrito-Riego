/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package request;

import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
public class AtributosFisicosRequest {
    
    private Integer idDetalle;
    
    private Map<String, Object> propiedades;

    public AtributosFisicosRequest() {
    }

    public AtributosFisicosRequest(Integer idDetalle, Map<String, Object> propiedades) {
        this.idDetalle = idDetalle;
        this.propiedades = propiedades;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Map<String, Object> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(Map<String, Object> propiedades) {
        this.propiedades = propiedades;
    }
}
