/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
public record ErrorResponse(
        String codigo,
        String mensaje,
        int estado,
        LocalDateTime fecha,
        Map<String, String> detalles // Ideal para errores de validación de campos
        ) {

}
