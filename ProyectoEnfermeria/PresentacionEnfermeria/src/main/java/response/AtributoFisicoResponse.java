/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
public class AtributoFisicoResponse {
    
    private Map<String, Object> propiedades;

    public AtributoFisicoResponse() {
    }

    public AtributoFisicoResponse(Map<String, Object> propiedades) {
        this.propiedades = propiedades;
    }

    public Map<String, Object> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(Map<String, Object> propiedades) {
        this.propiedades = propiedades;
    }
    
    
}
