/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
public class ExpedienteConfigResponse {
    
    private Map<String, Integer> antecedentes;
    
    private Map<String, Integer> atributos;
    
    private List<String> tiposSangre;

    public ExpedienteConfigResponse() {
    }

    public ExpedienteConfigResponse(Map<String, Integer> antecedentes, Map<String, Integer> atributos, List<String> tiposSangre) {
        this.antecedentes = antecedentes;
        this.atributos = atributos;
        this.tiposSangre = tiposSangre;
    }

    public Map<String, Integer> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(Map<String, Integer> antecedentes) {
        this.antecedentes = antecedentes;
    }

    public Map<String, Integer> getAtributos() {
        return atributos;
    }

    public void setAtributos(Map<String, Integer> atributos) {
        this.atributos = atributos;
    }

    public List<String> getTiposSangre() {
        return tiposSangre;
    }

    public void setTiposSangre(List<String> tiposSangre) {
        this.tiposSangre = tiposSangre;
    }
    
    
}
