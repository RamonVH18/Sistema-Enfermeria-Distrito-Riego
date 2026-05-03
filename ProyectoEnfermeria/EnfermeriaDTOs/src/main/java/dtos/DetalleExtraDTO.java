package dtos;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class DetalleExtraDTO {
    
    private Integer id;
    
    private String detalle;

    public DetalleExtraDTO() {}

    public DetalleExtraDTO(Integer id, String detalle) {
        this.id = id;
        this.detalle = detalle;
    }

    public DetalleExtraDTO(String detalle) {
        this.detalle = detalle;
    }

    public Integer getId() {return id;}

    public String getDetalle() {return detalle;}

    public void setId(Integer id) {this.id = id;}

    public void setDetalle(String detalle) {this.detalle = detalle;}    
}