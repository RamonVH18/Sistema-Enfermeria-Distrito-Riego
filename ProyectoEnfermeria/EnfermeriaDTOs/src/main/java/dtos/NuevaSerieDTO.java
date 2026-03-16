package dtos;

import enums.PeriodoCita;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class NuevaSerieDTO {
    // Atributos
    private PeriodoCita periodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime hora;

    // Constructores
    public NuevaSerieDTO() {}

    public NuevaSerieDTO(
            PeriodoCita periodo, 
            LocalDate fechaInicio, 
            LocalDate fechaFin, 
            LocalTime hora
    ) {
        this.periodo = periodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
    }

    // Getters
    public PeriodoCita getPeriodo() {return periodo;}

    public LocalDate getFechaInicio() {return fechaInicio;}

    public LocalDate getFechaFin() {return fechaFin;}

    public LocalTime getHora() {return hora;}

    // Setters
    public void setPeriodo(PeriodoCita periodo) {this.periodo = periodo;}

    public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}

    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}

    public void setHora(LocalTime hora) {this.hora = hora;}    
}