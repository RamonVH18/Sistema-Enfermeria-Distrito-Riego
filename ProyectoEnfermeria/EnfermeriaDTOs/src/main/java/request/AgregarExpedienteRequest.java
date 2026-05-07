/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package request;

import java.util.List;

/**
 *
 * @author Ramon Valencia
 */
public class AgregarExpedienteRequest {
    
    private Integer idEmpleado;
    private String nss;
    private String TipoSangre;
    private List<AntecedentesRequest> antecedentes;
    private List<AtributosFisicosRequest> atributos;

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

    public List<AtributosFisicosRequest> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<AtributosFisicosRequest> atributos) {
        this.atributos = atributos;
    }

    public AgregarExpedienteRequest(Integer idEmpleado, String nss, String TipoSangre, List<AntecedentesRequest> antecedentes, List<AtributosFisicosRequest> atributos) {
        this.idEmpleado = idEmpleado;
        this.nss = nss;
        this.TipoSangre = TipoSangre;
        this.antecedentes = antecedentes;
        this.atributos = atributos;
    }
    
    
    
    
}
