package dtos;

import java.time.LocalDate;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class RegistroMedicoDTO {
    
    private Integer id;    
    private String notas;
    private LocalDate fechaCreacion;
    private Float altura;
    private Float peso;
    private Float presionSistolica;
    private Float presionDiastolica;
    private Float glucosa;
    private Float oxigenacion;
    private Float frecuenciaCardiaca;
    private Float temperatura;
    private ExpedienteMedicoDTO expedienteMedico;
    
    public RegistroMedicoDTO() {}

    public RegistroMedicoDTO(
            String notas, 
            LocalDate fechaCreacion, 
            Float altura, 
            Float peso, 
            Float presionSistolica, 
            Float presionDiastolica, 
            Float glucosa, 
            Float oxigenacion, 
            Float frecuenciaCardiaca, 
            Float temperatura, 
            ExpedienteMedicoDTO expedienteMedico
    ) {
        this.notas = notas;
        this.fechaCreacion = fechaCreacion;
        this.altura = altura;
        this.peso = peso;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.glucosa = glucosa;
        this.oxigenacion = oxigenacion;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.temperatura = temperatura;
        this.expedienteMedico = expedienteMedico;
    }

    public RegistroMedicoDTO(
            Integer id, 
            String notas, 
            LocalDate fechaCreacion, 
            Float altura, 
            Float peso, 
            Float presionSistolica, 
            Float presionDiastolica, 
            Float glucosa, 
            Float oxigenacion, 
            Float frecuenciaCardiaca, 
            Float temperatura, 
            ExpedienteMedicoDTO expedienteMedico
    ) {
        this.id = id;
        this.notas = notas;
        this.fechaCreacion = fechaCreacion;
        this.altura = altura;
        this.peso = peso;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.glucosa = glucosa;
        this.oxigenacion = oxigenacion;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.temperatura = temperatura;
        this.expedienteMedico = expedienteMedico;
    }
    
    public Integer getId() {return id;}

    public String getNotas() {return notas;}

    public LocalDate getFechaCreacion() {return fechaCreacion;}

    public Float getAltura() {return altura;}

    public Float getPeso() {return peso;}

    public Float getPresionSistolica() {return presionSistolica;}

    public Float getPresionDiatolica() {return presionDiastolica;}

    public Float getGlucosa() {return glucosa;}

    public Float getOxigenacion() {return oxigenacion;}

    public Float getFrecuenciaCardiaca() {return frecuenciaCardiaca;}

    public Float getTemperatura() {return temperatura;}

    public ExpedienteMedicoDTO getExpedienteMedico() {return expedienteMedico;}

    public void setId(Integer id) {this.id = id;}

    public void setNotas(String notas) {this.notas = notas;}

    public void setFechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public void setAltura(Float altura) {this.altura = altura;}

    public void setPeso(Float peso) {this.peso = peso;}

    public void setPresionSistolica(Float presionSistolica) {this.presionSistolica = presionSistolica;}

    public void setPresionDiatolica(Float presionDiastolica) {this.presionDiastolica = presionDiastolica;}

    public void setGlucosa(Float glucosa) {this.glucosa = glucosa;}

    public void setOxigenacion(Float oxigenacion) {this.oxigenacion = oxigenacion;}

    public void setFrecuenciaCardiaca(Float frecuenciaCardiaca) {this.frecuenciaCardiaca = frecuenciaCardiaca;}

    public void setTemperatura(Float temperatura) {this.temperatura = temperatura;}

    public void setExpedienteMedico(ExpedienteMedicoDTO expedienteMedico) {this.expedienteMedico = expedienteMedico;}
}