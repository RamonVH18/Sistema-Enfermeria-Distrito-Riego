package response;

/**
 *
 * @author Ramon Valencia
 */
public class DatosEmpleadoResponse {

    private String nombreEmpleado;
    private Integer idEmpleado;
    private String nombreDepartamento;
    private int edad;
    private String tipoSangre;
    private String telefono;

    public DatosEmpleadoResponse() {
    }

    public DatosEmpleadoResponse(String nombreEmpleado, Integer idEmpleado, String nombreDepartamento, int edad, String tipoSangre, String telefono) {
        this.nombreEmpleado = nombreEmpleado;
        this.idEmpleado = idEmpleado;
        this.nombreDepartamento = nombreDepartamento;
        this.edad = edad;
        this.tipoSangre = tipoSangre;
        this.telefono = telefono;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
