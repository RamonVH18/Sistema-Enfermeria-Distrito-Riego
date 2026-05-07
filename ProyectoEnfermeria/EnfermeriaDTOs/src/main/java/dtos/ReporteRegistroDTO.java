package dtos;

import java.time.LocalDate;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ReporteRegistroDTO {

    private String nombre;
    private String departamento;
    private String sexo;
    private Integer edad;
    private String nss;
    private LocalDate fechaRegistro;
    private Float altura;
    private Float peso;
    private Float presionSistolica;
    private Float presionDiatolica;
    private Float glucosa;
    private Float oxigenacion;
    private Float frecuenciaCardiaca;
    private Float temperatura;

    public ReporteRegistroDTO() {}

    public ReporteRegistroDTO(
            String departamento, 
            String sexo, 
            Integer edad, 
            String nss, 
            LocalDate fechaRegistro, 
            Float altura, 
            Float peso, 
            Float presionSistolica, 
            Float presionDiatolica, 
            Float glucosa, 
            Float oxigenacion, 
            Float frecuenciaCardiaca, 
            Float temperatura
    ) {
        this.departamento = departamento;
        this.sexo = sexo;
        this.edad = edad;
        this.nss = nss;
        this.fechaRegistro = fechaRegistro;
        this.altura = altura;
        this.peso = peso;
        this.presionSistolica = presionSistolica;
        this.presionDiatolica = presionDiatolica;
        this.glucosa = glucosa;
        this.oxigenacion = oxigenacion;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.temperatura = temperatura;
    }

    public ReporteRegistroDTO(
            String nombre, 
            String departamento, 
            String sexo, 
            Integer edad, 
            String nss, 
            LocalDate fechaRegistro, 
            Float altura, 
            Float peso, 
            Float presionSistolica, 
            Float presionDiatolica, 
            Float glucosa, 
            Float oxigenacion, 
            Float frecuenciaCardiaca, 
            Float temperatura
    ) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.sexo = sexo;
        this.edad = edad;
        this.nss = nss;
        this.fechaRegistro = fechaRegistro;
        this.altura = altura;
        this.peso = peso;
        this.presionSistolica = presionSistolica;
        this.presionDiatolica = presionDiatolica;
        this.glucosa = glucosa;
        this.oxigenacion = oxigenacion;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.temperatura = temperatura;
    }

    public String getNombre() {return nombre;}

    public String getDepartamento() {return departamento;}

    public String getSexo() {return sexo;}

    public Integer getEdad() {return edad;}

    public String getNss() {return nss;}

    public LocalDate getFechaRegistro() {return fechaRegistro;}

    public Float getAltura() {return altura;}

    public Float getPeso() {return peso;}

    public Float getPresionSistolica() {return presionSistolica;}

    public Float getPresionDiatolica() {return presionDiatolica;}

    public Float getGlucosa() {return glucosa;}

    public Float getOxigenacion() {return oxigenacion;}

    public Float getFrecuenciaCardiaca() {return frecuenciaCardiaca;}

    public Float getTemperatura() {return temperatura;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setDepartamento(String departamento) {this.departamento = departamento;}

    public void setSexo(String sexo) {this.sexo = sexo;}

    public void setEdad(Integer edad) {this.edad = edad;}

    public void setNss(String nss) {this.nss = nss;}

    public void setFechaRegistro(LocalDate fechaRegistro) {this.fechaRegistro = fechaRegistro;}

    public void setAltura(Float altura) {this.altura = altura;}

    public void setPeso(Float peso) {this.peso = peso;}

    public void setPresionSistolica(Float presionSistolica) {this.presionSistolica = presionSistolica;}

    public void setPresionDiatolica(Float presionDiatolica) {this.presionDiatolica = presionDiatolica;}

    public void setGlucosa(Float glucosa) {this.glucosa = glucosa;}

    public void setOxigenacion(Float oxigenacion) {this.oxigenacion = oxigenacion;}

    public void setFrecuenciaCardiaca(Float frecuenciaCardiaca) {this.frecuenciaCardiaca = frecuenciaCardiaca;}

    public void setTemperatura(Float temperatura) {this.temperatura = temperatura;}
}