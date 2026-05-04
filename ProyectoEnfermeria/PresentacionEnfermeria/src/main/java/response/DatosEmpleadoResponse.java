/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public DatosEmpleadoResponse() {
    }

    public DatosEmpleadoResponse(String nombreEmpleado, int idEmpleado, String nombreDepartamento, int edad, String tipoSangre) {
        this.nombreEmpleado = nombreEmpleado;
        this.idEmpleado = idEmpleado;
        this.nombreDepartamento = nombreDepartamento;
        this.edad = edad;
        this.tipoSangre = tipoSangre;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
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

}
