package dtos;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class DepartamentoDTO {
    
    private String nombre;
    private Integer idJefeDepartamento;

    public DepartamentoDTO() {}

    public DepartamentoDTO(String nombre, Integer idJefeDepartamento) {
        this.nombre = nombre;
        this.idJefeDepartamento = idJefeDepartamento;
    }

    public String getNombre() {return nombre;}

    public Integer getIdJefeDepartamento() {return idJefeDepartamento;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setIdJefeDepartamento(Integer idJefeDepartamento) {this.idJefeDepartamento = idJefeDepartamento;}
}