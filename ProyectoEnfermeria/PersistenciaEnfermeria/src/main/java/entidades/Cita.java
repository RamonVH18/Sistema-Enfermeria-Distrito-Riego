/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoCita;
import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 */
public class Cita {
    private Integer id_cita;
    private LocalDateTime fecha_hora;
    private EstadoCita estado;
    private String motivo;
}
