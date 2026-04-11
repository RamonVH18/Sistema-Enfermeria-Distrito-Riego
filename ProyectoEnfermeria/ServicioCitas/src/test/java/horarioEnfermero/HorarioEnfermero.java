package horarioEnfermero;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Datos del horario laboral del enfermero.
 * Se debe actualizar antes de cada prueba si el horario del enfermero
 * es modificado. El objetivo de esta clase es evitar a toda costa una conexión
 * con la base de datos (si es que el horario se almacena en la misma).
 * @author Leonardo Flores Leyva - 252390
 */
public class HorarioEnfermero {
    // Límites de hora y duración de las citas (hardcodeado; debería ser almacenado en BD)
    public static final int DURACION_CITA = 15;
    public static final LocalTime HORA_INICIO_CITAS = LocalTime.of(8, 0, 0);
    public static final LocalTime HORA_TERMINO_CITAS = LocalTime.of(16, 0, 0);
    // Días laborales del enfermero (hardcodeado; debería ser almacenado en BD)
    public static final Set<DayOfWeek> diasLaborales = new HashSet<>(Arrays.asList(
            DayOfWeek.MONDAY, 
            DayOfWeek.TUESDAY, 
            DayOfWeek.WEDNESDAY, 
            DayOfWeek.THURSDAY, 
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
    ));
}