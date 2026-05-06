package response;

/**
 *
 * @author Ramon Valencia
 */
public class EmpleadoOptionResponse {

    private Integer idEmpleado;
    private String nombre;

    public EmpleadoOptionResponse() {}

    public EmpleadoOptionResponse(Integer idEmpleado, String nombre) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
    }

    public Integer getIdEmpleado() {return idEmpleado;}

    public void setIdEmpleado(Integer idEmpleado) {this.idEmpleado = idEmpleado;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}
}