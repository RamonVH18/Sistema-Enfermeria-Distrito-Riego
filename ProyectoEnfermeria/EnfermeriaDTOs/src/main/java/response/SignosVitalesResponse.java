package response;

/**
 *
 * @author Ramon Valencia
 */
public class SignosVitalesResponse {
    
    private Float altura;    
    private Float peso;    
    private Float presionSistolica;    
    private Float presionDiatolica;    
    private Float glucosa;    
    private Float oxigenacion;    
    private Float imc;    
    private Float frecuencia_cardiaca;    
    private Float temperatura;

    public SignosVitalesResponse() {}

    public SignosVitalesResponse(
            Float altura, 
            Float peso, 
            Float presionSistolica, 
            Float presionDiatolica, 
            Float glucosa, 
            Float oxigenacion, 
            Float imc, 
            Float frecuencia_cardiaca, 
            Float temperatura
    ) {

        this.altura = altura;
        this.peso = peso;
        this.presionSistolica = presionSistolica;
        this.presionDiatolica = presionDiatolica;
        this.glucosa = glucosa;
        this.oxigenacion = oxigenacion;
        this.imc = imc;
        this.frecuencia_cardiaca = frecuencia_cardiaca;
        this.temperatura = temperatura;
    }

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

    public Float getImc() {return imc;}

    public void setImc(Float imc) {this.imc = imc;}

    public Float getFrecuencia_cardiaca() {return frecuencia_cardiaca;}

    public void setFrecuencia_cardiaca(Float frecuencia_cardiaca) {
        this.frecuencia_cardiaca = frecuencia_cardiaca;
    }

    public Float getTemperatura() {return temperatura;}

    public void setTemperatura(Float temperatura) {this.temperatura = temperatura;}
}