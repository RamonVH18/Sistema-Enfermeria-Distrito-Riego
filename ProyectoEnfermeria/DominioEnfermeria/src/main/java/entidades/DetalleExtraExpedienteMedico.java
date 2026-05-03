package entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 *
 * @author Leonardo Flores Leyva
 */
@Entity
public class DetalleExtraExpedienteMedico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_extra_expediente")
    private Integer id;

    @Column(nullable = false, length = 500)
    private String valor;
    
    @ManyToOne(cascade = CascadeType.PERSIST) // Nos ahorra el crear el antecedente previamente
    @JoinColumn(name = "id_detalle_extra", nullable = false)
    private DetalleExtra detalleExtra;
    
    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private ExpedienteMedico expedienteMedico;

    public DetalleExtraExpedienteMedico() {}

    public DetalleExtraExpedienteMedico(
            String valor, 
            DetalleExtra detalleExtra, 
            ExpedienteMedico expedienteMedico
    ) {
        this.valor = valor;
        this.detalleExtra = detalleExtra;
        this.expedienteMedico = expedienteMedico;
    }

    public DetalleExtraExpedienteMedico(
            Integer id, 
            String valor, 
            DetalleExtra detalleExtra, 
            ExpedienteMedico expedienteMedico
    ) {
        this.id = id;
        this.valor = valor;
        this.detalleExtra = detalleExtra;
        this.expedienteMedico = expedienteMedico;
    }
    
    public Integer getId() {return id;}

    public String getValor() {return valor;}

    public DetalleExtra getDetalleExtra() {return detalleExtra;}

    public ExpedienteMedico getExpedienteMedico() {return expedienteMedico;}

    public void setId(Integer id) {this.id = id;}

    public void setValor(String valor) {this.valor = valor;}

    public void setDetalleExtra(DetalleExtra detalleExtra) {this.detalleExtra = detalleExtra;}

    public void setExpedienteMedico(ExpedienteMedico expedienteMedico) {this.expedienteMedico = expedienteMedico;}
}