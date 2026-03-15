package dtos;

import java.time.LocalDate;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class EmpleadoSinDireccionDTO {
    // Atributos de un empleado
    private Integer idEmpleado;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String curp;
    private String unidadTrabajo;
    private String genero;
    private String estado;
    // Departamento al que pertenece el empleado
    private DepartamentoDTO departamento;
    
    //Constructores
    public EmpleadoSinDireccionDTO() {}

    public EmpleadoSinDireccionDTO(
            Integer idEmpleado,
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            LocalDate fechaNacimiento, 
            String telefono, 
            String curp, 
            String unidadTrabajo, 
            String genero, 
            String estado,
            DepartamentoDTO departamento
    ) {
        this.idEmpleado = idEmpleado;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.curp = curp;
        this.unidadTrabajo = unidadTrabajo;
        this.genero = genero;
        this.estado = estado;
        this.departamento = departamento;
    }

    // Getters
    public Integer getIdEmpleado() {return idEmpleado;}
    
    public String getNombres() {return nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}

    public LocalDate getFechaNacimiento() {return fechaNacimiento;}

    public String getTelefono() {return telefono;}

    public String getCurp() {return curp;}

    public String getUnidadTrabajo() {return unidadTrabajo;}

    public String getGenero() {return genero;}

    public String getEstado() {return estado;}

    public DepartamentoDTO getDepartamento() {return departamento;}
    
    // Setters
    public void setIdEmpleado(Integer idEmpleado) {this.idEmpleado = idEmpleado;}
    
    public void setNombres(String nombres) {this.nombres = nombres;}

    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setCurp(String curp) {this.curp = curp;}

    public void setUnidadTrabajo(String unidadTrabajo) {this.unidadTrabajo = unidadTrabajo;}

    public void setGenero(String genero) {this.genero = genero;}

    public void setEstado(String estado) {this.estado = estado;}

    public void setDepartamento(DepartamentoDTO departamento) {this.departamento = departamento;}
}