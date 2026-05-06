package dtos;

import enums.TipoSangre;
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
    private Set<DetalleExtraExpedienteMedicoDTO> detallesExtra;

    public ExpedienteMedicoDTO() {}

    public ExpedienteMedicoDTO(
            TipoSangre tipoSangre, 
            Integer numeroSeguridadSocial, 
            Integer idEmpleado,
            Set<DetalleExtraExpedienteMedicoDTO> detallesExtra
    ) {
        this.tipoSangre = tipoSangre;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.idEmpleado = idEmpleado;
        this.detallesExtra = detallesExtra;
    }

    public ExpedienteMedicoDTO(
            Integer id, 
            TipoSangre tipoSangre, 
            Integer numeroSeguridadSocial, 
            Integer idEmpleado, 
            Set<DetalleExtraExpedienteMedicoDTO> detallesExtra
    ) {
        this.id = id;
        this.tipoSangre = tipoSangre;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.idEmpleado = idEmpleado;
    }
    
    public Integer getId() {return id;}

    public TipoSangre getTipoSangre() {return tipoSangre;}

    public Integer getNumeroSeguridadSocial() {return numeroSeguridadSocial;}

    public Integer getIdEmpleado() {return idEmpleado;}

    public Set<DetalleExtraExpedienteMedicoDTO> getDetallesExtra() {return detallesExtra;}

    public void setId(Integer id) {this.id = id;}

    public void setTipoSangre(TipoSangre tipoSangre) {this.tipoSangre = tipoSangre;}

    public void setNumeroSeguridadSocial(Integer numeroSeguridadSocial) {this.numeroSeguridadSocial = numeroSeguridadSocial;}

    public void setIdEmpleado(Integer idEmpleado) {this.idEmpleado = idEmpleado;}

    public void setDetallesExtra(Set<DetalleExtraExpedienteMedicoDTO> detallesExtra) {this.detallesExtra = detallesExtra;}
}