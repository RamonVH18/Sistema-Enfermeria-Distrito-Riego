/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import dtos.EmpleadoSinExpedienteDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
public class ExpedienteConfigResponse {
    
    private List<EmpleadoSinExpedienteDTO> empleado;
    
    private Map<String, Integer> antecedentes;
    
    private Map<String, Integer> atributos;
    
    private List<String> tiposSangre;

    public ExpedienteConfigResponse() {
    }

    public ExpedienteConfigResponse(List<EmpleadoSinExpedienteDTO> empleado, Map<String, Integer> antecedentes, Map<String, Integer> atributos, List<String> tiposSangre) {
        this.empleado = empleado;
        this.antecedentes = antecedentes;
        this.atributos = atributos;
        this.tiposSangre = tiposSangre;
    }

    public List<EmpleadoSinExpedienteDTO> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(List<EmpleadoSinExpedienteDTO> empleado) {
        this.empleado = empleado;
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
