package dtos;

import enums.TipoSangre;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ExpedienteMedicoDTO {
    
    private Integer id;
    
    private TipoSangre tipoSangre;
    
    private Integer numeroSeguridadSocial;
    
    private Integer idEmpleado;
    
    private Set<AntecedenteExpedienteMedicoDTO> antecedentes;
    
    private Set<AtributoFisicoExpedienteMedicoDTO> atributosFisicos;
    
    private Set<DetalleExtraExpedienteMedicoDTO> detallesExtra;
    
    private List<RegistroMedicoDTO> registrosMedicos;

    public ExpedienteMedicoDTO() {}

    public ExpedienteMedicoDTO(
            TipoSangre tipoSangre, 
            Integer numeroSeguridadSocial, 
            Integer idEmpleado, 
            Set<AntecedenteExpedienteMedicoDTO> antecedentes, 
            Set<AtributoFisicoExpedienteMedicoDTO> atributosFisicos, 
            Set<DetalleExtraExpedienteMedicoDTO> detallesExtra, 
            List<RegistroMedicoDTO> registrosMedicos
    ) {
        this.tipoSangre = tipoSangre;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.idEmpleado = idEmpleado;
        this.antecedentes = antecedentes;
        this.atributosFisicos = atributosFisicos;
        this.detallesExtra = detallesExtra;
        this.registrosMedicos = registrosMedicos;
    }

    public ExpedienteMedicoDTO(
            Integer id, 
            TipoSangre tipoSangre, 
            Integer numeroSeguridadSocial, 
            Integer idEmpleado, 
            Set<AntecedenteExpedienteMedicoDTO> antecedentes, 
            Set<AtributoFisicoExpedienteMedicoDTO> atributosFisicos, 
            Set<DetalleExtraExpedienteMedicoDTO> detallesExtra, 
            List<RegistroMedicoDTO> registrosMedicos
    ) {
        this.id = id;
        this.tipoSangre = tipoSangre;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.idEmpleado = idEmpleado;
        this.antecedentes = antecedentes;
        this.atributosFisicos = atributosFisicos;
        this.detallesExtra = detallesExtra;
        this.registrosMedicos = registrosMedicos;
    }
    
    public Integer getId() {return id;}

    public TipoSangre getTipoSangre() {return tipoSangre;}

    public Integer getNumeroSeguridadSocial() {return numeroSeguridadSocial;}

    public Integer getIdEmpleado() {return idEmpleado;}

    public Set<AntecedenteExpedienteMedicoDTO> getAntecedentes() {return antecedentes;}

    public Set<AtributoFisicoExpedienteMedicoDTO> getAtributosFisicos() {return atributosFisicos;}

    public Set<DetalleExtraExpedienteMedicoDTO> getDetallesExtra() {return detallesExtra;}

    public List<RegistroMedicoDTO> getRegistrosMedicos() {return registrosMedicos;}

    public void setId(Integer id) {this.id = id;}

    public void setTipoSangre(TipoSangre tipoSangre) {this.tipoSangre = tipoSangre;}

    public void setNumeroSeguridadSocial(Integer numeroSeguridadSocial) {this.numeroSeguridadSocial = numeroSeguridadSocial;}

    public void setIdEmpleado(Integer idEmpleado) {this.idEmpleado = idEmpleado;}

    public void setAntecedentes(Set<AntecedenteExpedienteMedicoDTO> antecedentes) {this.antecedentes = antecedentes;}

    public void setAtributosFisicos(Set<AtributoFisicoExpedienteMedicoDTO> atributosFisicos) {this.atributosFisicos = atributosFisicos;}

    public void setDetallesExtra(Set<DetalleExtraExpedienteMedicoDTO> detallesExtra) {this.detallesExtra = detallesExtra;}

    public void setRegistrosMedicos(List<RegistroMedicoDTO> registrosMedicos) {this.registrosMedicos = registrosMedicos;}
}