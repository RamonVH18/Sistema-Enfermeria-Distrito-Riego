package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Ramon Valencia
 */
@Entity
public class RegistroMedico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_medico")
    private Integer id;

    @Column(length = 1000)
    private String notas;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private Float altura;

    @Column(nullable = false)
    private Float peso;

    @Column(name = "presion_sistolica", nullable = false)
    private Float presionSistolica;

    @Column(name = "presion_diatolica", nullable = false)
    private Float presionDiatolica;

    @Column(nullable = false)
    private Float glucosa;

    @Column(nullable = false)
    private Float oxigenacion;

    @Column(name = "frecuencia_cardiaca", nullable = false)
    private Float frecuenciaCardiaca;

    @Column(nullable = false)
    private Float temperatura;

    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private ExpedienteMedico expedienteMedico;

    // Pendiente el resto de atributos y métodos...
    public RegistroMedico() {}

    public RegistroMedico(
            Integer id, 
            String notas, 
            LocalDate fechaCreacion, 
            Float altura, 
            Float peso, 
            Float presionSistolica, 
            Float presionDiatolica, 
            Float glucosa, 
            Float oxigenacion, 
            Float frecuenciaCardiaca, 
            Float temperatura, 
            ExpedienteMedico expedienteMedico
    ) {
        this.id = id;
        this.notas = notas;
        this.fechaCreacion = fechaCreacion;
        this.altura = altura;
        this.peso = peso;
        this.presionSistolica = presionSistolica;
        this.presionDiatolica = presionDiatolica;
        this.glucosa = glucosa;
        this.oxigenacion = oxigenacion;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.temperatura = temperatura;
        this.expedienteMedico = expedienteMedico;
    }
    
    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNotas() {return notas;}

    public void setNotas(String notas) {this.notas = notas;}

    public LocalDate getFechaCreacion() {return fechaCreacion;}

    public void setFechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public Float getAltura() {return altura;}

    public void setAltura(Float altura) {this.altura = altura;}

    public Float getPeso() {return peso;}

    public void setPeso(Float peso) {this.peso = peso;}

    public Float getPresionSistolica() {return presionSistolica;}

    public void setPresionSistolica(Float presionSistolica) {this.presionSistolica = presionSistolica;}

    public Float getPresionDiatolica() {return presionDiatolica;}

    public void setPresionDiatolica(Float presionDiatolica) {this.presionDiatolica = presionDiatolica;}

    public Float getGlucosa() {return glucosa;}

    public void setGlucosa(Float glucosa) {this.glucosa = glucosa;}

    public Float getOxigenacion() {return oxigenacion;}

    public void setOxigenacion(Float oxigenacion) {this.oxigenacion = oxigenacion;}

    public Float getFrecuenciaCardiaca() {return frecuenciaCardiaca;}

    public void setFrecuenciaCardiaca(Float frecuenciaCardiaca) {this.frecuenciaCardiaca = frecuenciaCardiaca;}

    public Float getTemperatura() {return temperatura;}

    public void setTemperatura(Float temperatura) {this.temperatura = temperatura;}

    public ExpedienteMedico getExpedienteMedico() {return expedienteMedico;}

    public void setExpedienteMedico(ExpedienteMedico expedienteMedico) {this.expedienteMedico = expedienteMedico;}   
}