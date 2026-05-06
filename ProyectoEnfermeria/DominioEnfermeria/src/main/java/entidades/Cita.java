package entidades;

import enums.EstadoCita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva - 252390
 */
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCita estado;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_enfermero", nullable = false)
    private Enfermero enfermero;

    public Cita() {}

    public Cita(
            LocalDateTime fechaHora,
            EstadoCita estado,
            String motivo,
            Empleado empleado,
            Enfermero enfermero
    ) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.motivo = motivo;
        this.empleado = empleado;
        this.enfermero = enfermero;
    }

    public Cita(
            Integer idCita,
            LocalDateTime fechaHora,
            EstadoCita estado,
            String motivo,
            Empleado empleado,
            Enfermero enfermero
    ) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.motivo = motivo;
        this.empleado = empleado;
        this.enfermero = enfermero;
    }

    public Integer getIdCita() {return idCita;}

    public void setIdCita(Integer idCita) {this.idCita = idCita;}

    public LocalDateTime getFechaHora() {return fechaHora;}

    public void setFechaHora(LocalDateTime fechaHora) {this.fechaHora = fechaHora;}

    public EstadoCita getEstado() {return estado;}

    public void setEstado(EstadoCita estado) {this.estado = estado;}

    public String getMotivo() {return motivo;}

    public void setMotivo(String motivo) {this.motivo = motivo;}

    public Empleado getEmpleado() {return empleado;}

    public void setEmpleado(Empleado empleado) {this.empleado = empleado;}

    public Enfermero getEnfermero() {return enfermero;}

    public void setEnfermero(Enfermero enfermero) {this.enfermero = enfermero;}
}