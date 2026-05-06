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
 * @author Ramon Valencia 
 */
@Entity
public class DetalleExtra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_extra")
    private Integer id;

    @Column(nullable = false, length = 500)
    private String valor;

    @ManyToOne(cascade = CascadeType.PERSIST) // Nos ahorra el crear el antecedente previamente
    @JoinColumn(name = "id_detalle", nullable = false)
    private Detalle detalle;

    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private ExpedienteMedico expedienteMedico;

    public DetalleExtra() {}

    public DetalleExtra(
            String valor,
            Detalle detalle,
            ExpedienteMedico expedienteMedico
    ) {
        this.valor = valor;
        this.detalle = detalle;
        this.expedienteMedico = expedienteMedico;
    }

    public DetalleExtra(
            Integer id,
            String valor,
            Detalle detalle,
            ExpedienteMedico expedienteMedico
    ) {
        this.id = id;
        this.valor = valor;
        this.detalle = detalle;
        this.expedienteMedico = expedienteMedico;
    }

    public Integer getId() {return id;}

    public String getValor() {return valor;}

    public Detalle getDetalle() {return detalle;}

    public ExpedienteMedico getExpedienteMedico() {return expedienteMedico;}

    public void setId(Integer id) {this.id = id;}

    public void setValor(String valor) {this.valor = valor;}

    public void setDetalle(Detalle detalle) {this.detalle = detalle;}

    public void setExpedienteMedico(ExpedienteMedico expedienteMedico) {
        this.expedienteMedico = expedienteMedico;
    }
}