package dtos;

import java.time.LocalDate;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class NuevoEmpleadoDTO {
    // Atributos de un empleado
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String curp;
    private String unidadTrabajo;
    private String genero;
    private String estado;
    // Dirección del empleado
    private int numero;
    private String calle;
    private String municipio;
    private int cp;
    private String ciudad;
    // Departamento al que pertenece el empleado
    private DepartamentoDTO departamento;

    // Constructores
    public NuevoEmpleadoDTO() {}

    public NuevoEmpleadoDTO(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            LocalDate fechaNacimiento, 
            String telefono, 
            String curp, 
            String unidadTrabajo, 
            String genero, 
            String estado, 
            int numero, 
            String calle, 
            String municipio, 
            int cp, 
            String ciudad, 
            DepartamentoDTO departamento
    ) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.curp = curp;
        this.unidadTrabajo = unidadTrabajo;
        this.genero = genero;
        this.estado = estado;
        this.numero = numero;
        this.calle = calle;
        this.municipio = municipio;
        this.cp = cp;
        this.ciudad = ciudad;
        this.departamento = departamento;
    }

    // Getters
    public String getNombres() {return nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}

    public LocalDate getFechaNacimiento() {return fechaNacimiento;}

    public String getTelefono() {return telefono;}

    public String getCurp() {return curp;}

    public String getUnidadTrabajo() {return unidadTrabajo;}

    public String getGenero() {return genero;}

    public String getEstado() {return estado;}

    public int getNumero() {return numero;}

    public String getCalle() {return calle;}

    public String getMunicipio() {return municipio;}

    public int getCp() {return cp;}

    public String getCiudad() {return ciudad;}

    public DepartamentoDTO getDepartamento() {return departamento;}

    // Setters
    public void setNombres(String nombres) {this.nombres = nombres;}

    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setCurp(String curp) {this.curp = curp;}

    public void setUnidadTrabajo(String unidadTrabajo) {this.unidadTrabajo = unidadTrabajo;}

    public void setGenero(String genero) {this.genero = genero;}

    public void setEstado(String estado) {this.estado = estado;}

    public void setNumero(int numero) {this.numero = numero;}

    public void setCalle(String calle) {this.calle = calle;}

    public void setMunicipio(String municipio) {this.municipio = municipio;}

    public void setCp(int cp) {this.cp = cp;}

    public void setCiudad(String ciudad) {this.ciudad = ciudad;}

    public void setDepartamento(DepartamentoDTO departamento) {this.departamento = departamento;}
}