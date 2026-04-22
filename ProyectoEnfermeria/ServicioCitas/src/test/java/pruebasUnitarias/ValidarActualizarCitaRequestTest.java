package pruebasUnitarias;

import DAOs.CitaRepository;
import DAOs.EmpleadoRepository;
import DAOs.EnfermeroRepository;
import entidades.Cita;
import entidades.Departamento;
import entidades.DireccionEmpleado;
import entidades.Empleado;
import entidades.Enfermero;
import enums.EstadoCita;
import enums.EstadoEmpleado;
import horarioEnfermero.HorarioEnfermero;
import interfaces.IServicioCitas;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import request.ActualizarCitaRequest;
import servicios.ServicioCitas;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
@ExtendWith(SpringExtension.class) // Levanta el contexto de SpringBoot = obligatorio
@Import({
    ServicioCitas.class, // Importante incluir la clase que implementa la interfaz de IServicioCitas
    MethodValidationPostProcessor.class // IMPORTANTE INCLUIR ESTA CLASE PARA ACTIVAR LAS ETIQUETAS DE VALIDACIÓN
})
public class ValidarActualizarCitaRequestTest {
    
    // Se mockean los repositorios (la base de datos)
    @MockitoBean
    private CitaRepository citaRepository;
    @MockitoBean
    private EmpleadoRepository empleadoRepository;
    @MockitoBean
    private EnfermeroRepository enfermeroRepository;
    
    // Se inyectan los repositorios mockeados al servicio de citas
    @Autowired
    private IServicioCitas servicioCitas;
    
    /*
        Se crean atributos con valores ideales para evitar la repetición de código.
    */
    private static final Integer ID_CITA_IDEAL = 1;
    private static LocalDateTime NUEVA_FECHA_HORA_IDEAL;
    
    // Solicitud a validar
    private ActualizarCitaRequest nuevaCita = new ActualizarCitaRequest();
    
    // Cita original ficticia
    private static Cita citaOriginal;
    
    @BeforeAll
    public static void setup(){
        // Días de diferencia respecto a la nueva fecha ideal y a la fecha actual
        int diasTranscurridos = 7;
        // Establece la fecha ideal como la de hoy a las 3:30 p.m.
        NUEVA_FECHA_HORA_IDEAL = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30, 0)).plusDays(7);
        // Se verifica que la fecha a probar esté dentro del horario del enfermero y esté en el futuro
        while(!HorarioEnfermero.diasLaborales.contains(NUEVA_FECHA_HORA_IDEAL.getDayOfWeek()) || NUEVA_FECHA_HORA_IDEAL.minusDays(7).isBefore(LocalDateTime.now())){
            NUEVA_FECHA_HORA_IDEAL = NUEVA_FECHA_HORA_IDEAL.plusDays(1);
            diasTranscurridos++;
        }
        
        citaOriginal = new Cita(
                ID_CITA_IDEAL,
                NUEVA_FECHA_HORA_IDEAL.minusDays(7),
                EstadoCita.PENDIENTE,
                "Chequeo general",
                null,
                new Empleado(
                        1, "Leonardo ", "Flores", "Leyva",
                        LocalDate.of(1991, Month.JULY, 3),
                        "6441549274",
                        "GAMC850820HDFRRR05",
                        "Obregón",
                        "Masculino",
                        EstadoEmpleado.ACTIVO,
                        new Departamento(1, "Contaduría"),
                        new DireccionEmpleado(20, "Miguel Hidalgo", "Cajeme", 85286, "Obregón")
                ),
                new Enfermero(
                        2,
                        new Empleado(
                                2, "Ramón", "Valencia", "Hernández",
                                LocalDate.of(1984, Month.OCTOBER, 20),
                                "6442548932",
                                "RGHE850820HDFRRR09",
                                "Obregón",
                                "Pansexual",
                                EstadoEmpleado.ACTIVO,
                                new Departamento(2, "Enfermería"),
                                new DireccionEmpleado(88, "Agustín de Iturbide", "Valle Dorado", 85291, "Obregón")
                        ),
                        new ArrayList<>()
                )
        );
        
        // Indica la fecha a probar
        System.out.printf("Nueva cita ideal: %d/%d/%d : %d:%d:%d \n", 
                NUEVA_FECHA_HORA_IDEAL.getDayOfMonth(),
                NUEVA_FECHA_HORA_IDEAL.getMonthValue(),
                NUEVA_FECHA_HORA_IDEAL.getYear(),
                NUEVA_FECHA_HORA_IDEAL.getHour(),
                NUEVA_FECHA_HORA_IDEAL.getMinute(),
                NUEVA_FECHA_HORA_IDEAL.getSecond()
        );
        
        // Obtiene la fecha de la cita original
        LocalDateTime fechaOriginal = citaOriginal.getFechaHora();
        // Indica la fecha de la cita original a probar
        System.out.printf("Cita original: %d/%d/%d : %d:%d:%d \n", 
                fechaOriginal.getDayOfMonth(),
                fechaOriginal.getMonthValue(),
                fechaOriginal.getYear(),
                fechaOriginal.getHour(),
                fechaOriginal.getMinute(),
                fechaOriginal.getSecond()
        );
        
        // Indica los días transcurridos a partir de la fecha actual
        System.out.printf("Dias transcurridos: %d\n", diasTranscurridos);
    }
    
    /**
     * Establece los atributos de la nueva nuevaCita con los ideales antes
     * de cada prueba con el objetivo de no repetir tanto código.
     */
    @BeforeEach
    public void resetCita(){
        // Establece los atributos ideales a nuevaCita.
        nuevaCita.setIdCita(ID_CITA_IDEAL);
        nuevaCita.setNuevaFechaHora(NUEVA_FECHA_HORA_IDEAL);
        // Omite las validaciones de negocio
        when(citaRepository.findById(nuevaCita.getIdCita())).thenReturn(Optional.of(citaOriginal));
        when(citaRepository.findByFechaHora(nuevaCita.getNuevaFechaHora())).thenReturn(Optional.empty());
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita(
                citaOriginal.getIdCita(), 
                NUEVA_FECHA_HORA_IDEAL, 
                citaOriginal.getEstado(), 
                citaOriginal.getMotivo(), 
                citaOriginal.getSerie(), 
                citaOriginal.getEmpleado(), 
                citaOriginal.getEnfermero())
        );
    }
    
    /**
     * Verifica que el id de la nueva nuevaCita no sea null
     */
    @Test
    public void asc01(){
        
        // Modifica el id de la nueva cita
        nuevaCita.setIdCita(null);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Cita requerida."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que el id de la nueva nuevaCita sea diferente de cero
     */
    @Test
    public void asc02(){
        
        // Modifica el id del enfermero
        nuevaCita.setIdCita(0);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Cita requerida."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que el id de la nueva nuevaCita no sea negativo
     */
    @Test
    public void asc03(){
        
        // Modifica el id del enfermero
        nuevaCita.setIdCita(-1);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Cita requerida."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que la fecha no sea null
     */
    @Test
    public void asc04(){
        // Modifica el minuto de la fecha y hora de la cita
        LocalDateTime fechaHoraInvalida = null;
        // Asigna la fecha y hora inválida a la cita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Fecha y hora requerida."));
    }
    /**
     * Verifica que el minuto de la fecha y hora de la nuevaCita sea múltiplo de 15.
     */
    @Test
    public void asc05(){
        // Calcula una hora que no sea múltiplo de la duración de la cita
        int horaInvalida = NUEVA_FECHA_HORA_IDEAL.getMinute();
        while(horaInvalida % HorarioEnfermero.DURACION_CITA == 0 && horaInvalida < 60)
            horaInvalida++;
        
        // Modifica el minuto de la fecha y hora de la cita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                NUEVA_FECHA_HORA_IDEAL.toLocalDate(), 
                LocalTime.of(NUEVA_FECHA_HORA_IDEAL.getHour(), horaInvalida, NUEVA_FECHA_HORA_IDEAL.getSecond())
        );
        
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Cada cita debe tener una duración de 15 minutos"));
    }
    /**
     * Verifica que el segundo de la fecha y hora de la nuevaCita sea igual a cero.
     */
    @Test
    public void asc06(){
        // Modifica el segundo de la fecha y hora de la nuevaCita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                NUEVA_FECHA_HORA_IDEAL.toLocalDate(), 
                LocalTime.of(NUEVA_FECHA_HORA_IDEAL.getHour(), NUEVA_FECHA_HORA_IDEAL.getMinute(), 57)
        );
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Cada cita debe tener una hora exacta."));
    }
    /**
     * Verifica que la fecha y hora de la nuevaCita sea mayor o igual que la de entrada 
     * del enfermero (08:00:00 a.m).
     */
    @Test
    public void asc07(){
        // Obtiene la hora de inicio
        LocalTime horaInicial = HorarioEnfermero.HORA_INICIO_CITAS;
        // Hora mínima posible (nunca debería llegar a esta hora; ningún enfermero trabaja a partir de la medianoche)
        LocalTime horaMinima = LocalTime.of(0, 0);
        // Resta una hora a la hora de inicio
        LocalTime horaInvalida = horaInicial.minusHours(1);
        
        // Verifica que sea mayor o igual que la medianoche
        if(horaInvalida.isAfter(horaInicial))
            horaInvalida = horaMinima; // Fallará la prueba, pero no se lanzará la excepción de la prueba csc11
        
        // Modifica la hora de la fecha y hora de la cita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                NUEVA_FECHA_HORA_IDEAL.toLocalDate(),
                horaInvalida // Se asigna la hora inválida
        );
        
        // Se verifica que la fecha a probar esté dentro del horario del enfermero y esté en el futuro
        while(!HorarioEnfermero.diasLaborales.contains(fechaHoraInvalida.getDayOfWeek()) || fechaHoraInvalida.isBefore(LocalDateTime.now()))
            fechaHoraInvalida = fechaHoraInvalida.plusDays(1);
        
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La cita no puede estar antes del período de citas."));
    }
    /**
     * Verifica que la fecha y hora de la nuevaCita sea menor que la de salida
     * del enfermero (04:00:00 p.m).
     */
    @Test
    public void asc08(){
        // Obtiene la hora de salida
        LocalTime horaInicial = HorarioEnfermero.HORA_TERMINO_CITAS;
        // Obtiene el límite máximo
        LocalTime horaMaxima = LocalTime.of(23, 59, 59);
        // Suma una hora a la hora de salida
        LocalTime horaInvalida = horaInicial.plusHours(1);
        
        // Verifica que la hora inválida sea menor que la media noche
        if(horaInvalida.isBefore(horaInicial))
            horaInvalida = horaMaxima; // Fallará la prueba, pero no se lanzará la excepción de la prueba csc10
        
        // Modifica la hora de la fecha y hora de la cita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                NUEVA_FECHA_HORA_IDEAL.toLocalDate(), 
                horaInvalida // Se suma una hora a la hora de termino de citas y se asigna el resultado
        );
        
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La cita debe estar antes de la hora de término de citas."));
    }
    /**
     * Verifica que la fecha y hora esté ubicada en el futuro:
     * No está en el pasado.
     */
    @Test
    public void asc09(){
        // Le resta un día a la fecha actual en una hora válida
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(LocalDate.now(), NUEVA_FECHA_HORA_IDEAL.toLocalTime());
        // Si la fecha inválida está fuera del horario del enfermero o después de la fecha y hora actuales, se le sigue restando 1 día
        while(!HorarioEnfermero.diasLaborales.contains(fechaHoraInvalida.getDayOfWeek()) || fechaHoraInvalida.isAfter(LocalDateTime.now()))
            fechaHoraInvalida = fechaHoraInvalida.minusDays(1);
        
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La fecha y hora debe ser futura."));
    }
    /**
     * Verifica que la fecha y hora esté ubicada en el futuro:
     * No está en el presente. Se recomienda que la prueba se
     * ejecute en un minuto múltiplo de 15.
     */
    @Test
    public void asc10(){
        // Obtiene la fecha y hora actual (el segundo se establece a cero)
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute())
        );
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La fecha y hora debe ser futura."));
    }
    
    /**
     * Verifica que la fecha y hora esté ubicada dentro del horario del
     * enfermero. ¡OJO! Se asume que el enfermero no trabaja los domingos.
     */
    @Test
    public void asc11(){
        // Establece el día de la fecha en un domingo
        LocalDateTime fechaHoraInvalida = NUEVA_FECHA_HORA_IDEAL;
        while(!fechaHoraInvalida.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            fechaHoraInvalida = fechaHoraInvalida.plusDays(1);
        
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La cita está fuera del horario laboral del enfermero."));
    }
    
    /**
     * Verifica que se permita la actualización de la cita si todos los datos
     * son válidos.
     */
    @Test
    public void asc12(){
        // Verifica que no se lance ninguna excepción
        assertDoesNotThrow(() -> {servicioCitas.actualizar(nuevaCita);});
        // Verifica si se llamó exactamente una vez el método save() del repositorio de citas
        verify(citaRepository, times(1)).save(any(Cita.class));
    }
}