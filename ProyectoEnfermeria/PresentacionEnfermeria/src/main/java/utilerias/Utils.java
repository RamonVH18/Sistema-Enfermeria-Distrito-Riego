/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Ramon Valencia
 */
public class Utils {

    public static String obtenerFechaTraducida(LocalDate fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fechaString = fecha.format(formatter);

        return fechaString.substring(0, 1).toUpperCase() + fechaString.substring(1);
    }

    public static String formatoFecha(LocalDate fecha) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(format);
    }

    public static String formatoHora(LocalTime hora) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return hora.format(format);
    }
}
