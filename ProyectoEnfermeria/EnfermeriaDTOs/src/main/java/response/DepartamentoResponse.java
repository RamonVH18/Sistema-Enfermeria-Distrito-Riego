package response;

/**
 *
 * @author isaac
 */
public class DepartamentoResponse {

    private String nombre;
    private Integer idJefeDepartamento;

    public DepartamentoResponse() {}

    public DepartamentoResponse(String nombre, Integer idJefeDepartamento) {
        this.nombre = nombre;
        this.idJefeDepartamento = idJefeDepartamento;
    }

    // Getters y Setters
    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public Integer getIdJefeDepartamento() {return idJefeDepartamento;}

    public void setIdJefeDepartamento(Integer idJefeDepartamento) {
        this.idJefeDepartamento = idJefeDepartamento;
    }
}