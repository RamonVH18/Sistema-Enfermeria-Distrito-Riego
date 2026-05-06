package dtos;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class DetalleExtraExpedienteMedicoDTO {
    
    private Integer id;
    private String valor;    
    private DetalleExtraDTO detalleExtra;    
    private ExpedienteMedicoDTO expedienteMedico;

    public DetalleExtraExpedienteMedicoDTO() {}

    public DetalleExtraExpedienteMedicoDTO(
            String valor, 
            DetalleExtraDTO detalleExtra, 
            ExpedienteMedicoDTO expedienteMedico
    ) {
        this.valor = valor;
        this.detalleExtra = detalleExtra;
        this.expedienteMedico = expedienteMedico;
    }

    public DetalleExtraExpedienteMedicoDTO(
            Integer id, 
            String valor, 
            DetalleExtraDTO detalleExtra, 
            ExpedienteMedicoDTO expedienteMedico
    ) {
        this.id = id;
        this.valor = valor;
        this.detalleExtra = detalleExtra;
        this.expedienteMedico = expedienteMedico;
    }

    public Integer getId() {return id;}

    public String getValor() {return valor;}

    public DetalleExtraDTO getDetalleExtra() {return detalleExtra;}

    public ExpedienteMedicoDTO getExpedienteMedico() {return expedienteMedico;}

    public void setId(Integer id) {this.id = id;}

    public void setValor(String valor) {this.valor = valor;}

    public void setDetalleExtra(DetalleExtraDTO detalleExtra) {this.detalleExtra = detalleExtra;}

    public void setExpedienteMedico(ExpedienteMedicoDTO expedienteMedico) {this.expedienteMedico = expedienteMedico;}
}