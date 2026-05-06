package response;

/**
 *
 * @author Ramon Valencia
 */
public class DetalleResponse {
    
    private String nombreDetalle;
    private String especificacion;

    public DetalleResponse() {}

    public DetalleResponse(String nombreDetalle, String especificacion) {
        this.nombreDetalle = nombreDetalle;
        this.especificacion = especificacion;
    }

    public String getNombreDetalle() {return nombreDetalle;}

    public void setNombreDetalle(String nombreDetalle) {this.nombreDetalle = nombreDetalle;}

    public String getEspecificacion() {return especificacion;}

    public void setEspecificacion(String especificacion) {this.especificacion = especificacion;}   
}