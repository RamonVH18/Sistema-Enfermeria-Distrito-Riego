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
 * @author Leonardo Flores Leyva
 */
@Entity
public class RegistroMedico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_medico")
    private Integer id;

    @Column(length = 1000)
    private String notas;
    
    @Column(name = "fecha_creacion",nullable = false)
    private LocalDate fechaCreacion;
    
    @Column(nullable = false)
    private Float altura;
    
    @Column(nullable = false)
    private Integer peso;
    
    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private ExpedienteMedico expedienteMedico;
    
    // Pendiente el resto de atributos y métodos...
}