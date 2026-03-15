/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.PeriodoCita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Ramon Valencia
 */
@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSerie")
    private Integer idSerie;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodo", nullable = false)
    private PeriodoCita periodo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    public Serie() {
    }

    public Serie(PeriodoCita periodo, LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora) {
        this.periodo = periodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
    }

    public Serie(Integer id, PeriodoCita periodo, LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora) {
        this.idSerie = id;
        this.periodo = periodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
    }

    public Integer getId() {
        return idSerie;
    }

    public void setId(Integer id) {
        this.idSerie = id;
    }

    public PeriodoCita getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoCita periodo) {
        this.periodo = periodo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Serie{"
                + "id=" + idSerie
                + ", periodo=" + periodo
                + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin
                + ", hora=" + hora
                + '}';
    }

}
