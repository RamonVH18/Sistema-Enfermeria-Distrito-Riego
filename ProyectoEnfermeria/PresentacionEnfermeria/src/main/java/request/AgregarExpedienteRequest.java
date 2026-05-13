/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package request;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
public class AgregarExpedienteRequest {
    
    private Integer idEmpleado;
    private String nss;
    private String TipoSangre;
    private List<AntecedentesRequest> antecedentes;
    private Map<String, AtributosFisicosRequest> atributos;

    public AgregarExpedienteRequest() {
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getTipoSangre() {
        return TipoSangre;
    }

    public void setTipoSangre(String TipoSangre) {
        this.TipoSangre = TipoSangre;
    }

    public List<AntecedentesRequest> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<AntecedentesRequest> antecedentes) {
        this.antecedentes = antecedentes;
    }

    public Map<String, AtributosFisicosRequest> getAtributos() {
        return atributos;
    }

    public void setAtributos(Map<String, AtributosFisicosRequest> atributos) {
        this.atributos = atributos;
    }

    public AgregarExpedienteRequest(Integer idEmpleado, String nss, String TipoSangre, List<AntecedentesRequest> antecedentes, Map<String, AtributosFisicosRequest> atributos) {
        this.idEmpleado = idEmpleado;
        this.nss = nss;
        this.TipoSangre = TipoSangre;
        this.antecedentes = antecedentes;
        this.atributos = atributos;
    }
    
}
