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
import request.CrearCitaRequest;
import servicios.ServicioCitas;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
@ExtendWith(SpringExtension.class) 
@Import({ServicioCitas.class, MethodValidationPostProcessor.class})
public class ValidarCrearCitaRequest {
    
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
        Se crean atributos con valores ideales para evitar la repetición de código
        ¡Ojo! El valor de FECHA_HORA_IDEAL está sujeto al tiempo. Si no se desea actualizar
        su valor en cada prueba, se puede codificar para que se ajuste a partir de la fecha
        actual. En esta primera versión, las pruebas están contempladas para ser ejecutadas
        el 14 de abril del 2026. Si ya realizó esta actualización, puede eliminar este
        comentario.
    */
    private static final Integer ID_EMPLEADO_IDEAL = 1;
    private static final Integer ID_ENFERMERO_IDEAL = 2;
    private static LocalDateTime FECHA_HORA_IDEAL;
    private static final String MOTIVO_IDEAL = "Chequeo general";
    
    // Días de diferencia respecto a la fecha ideal y a la fecha actual
    private static int diasTranscurridos = 1;
    
    // Petición a validar
    private CrearCitaRequest cita = new CrearCitaRequest();
    
    // Empleado ficticio
    private final Empleado empleadoFicticio = new Empleado(
            ID_EMPLEADO_IDEAL, 
            "Leonardo ",
            "Flores",
            "Leyva",
            LocalDate.of(1991, Month.JULY, 3), 
            "6441549274",
            "GAMC850820HDFRRR05", 
            "Obregón",
            "Masculino",
            EstadoEmpleado.ACTIVO, 
            new Departamento(1, "Contaduría"),
            new DireccionEmpleado(20, "Miguel Hidalgo", "Cajeme", 85286, "Obregón")
    );
    
    // Enfermero ficticio
    private Enfermero enfermeroFicticio = new Enfermero(
            ID_ENFERMERO_IDEAL, 
            new Empleado(
                ID_ENFERMERO_IDEAL, 
                "Ramón",
                "Valencia",
                "Hernández",
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
    );
    
    @BeforeAll
    static void setup(){
        // Establece la fecha ideal como la de hoy a las 3:30 p.m.
        FECHA_HORA_IDEAL = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30, 0));
        // Se verifica que la fecha a probar esté dentro del horario del enfermero y esté en el futuro
        while(!HorarioEnfermero.diasLaborales.contains(FECHA_HORA_IDEAL.getDayOfWeek()) || FECHA_HORA_IDEAL.isBefore(LocalDateTime.now())){
            FECHA_HORA_IDEAL = FECHA_HORA_IDEAL.plusDays(1);
            diasTranscurridos++;
        }
        // Indica la fecha a probar
        System.out.printf("%d/%d/%d : %d:%d:%d \n", 
                FECHA_HORA_IDEAL.getDayOfMonth(),
                FECHA_HORA_IDEAL.getMonthValue(),
                FECHA_HORA_IDEAL.getYear(),
                FECHA_HORA_IDEAL.getHour(),
                FECHA_HORA_IDEAL.getMinute(),
                FECHA_HORA_IDEAL.getSecond()
        );
        // Indica los días transcurridos a partir de la fecha actual
        System.out.printf("Dias transcurridos: %d\n", diasTranscurridos);
    }
    
    /**
     * Establece los atributos de la cita con los ideales antes
     * de cada prueba con el objetivo de no repetir tanto código.
     */
    @BeforeEach
    public void resetCita(){
        // Establece los atributos ideales a la cita.
        cita.setIdEmpleado(ID_EMPLEADO_IDEAL);
        cita.setIdEnfermero(ID_ENFERMERO_IDEAL);
        cita.setFechaHora(FECHA_HORA_IDEAL);
        cita.setMotivo(MOTIVO_IDEAL);
        
        // Omite las validaciones de negocio
        when(enfermeroRepository.findById(cita.getIdEnfermero())).thenReturn(Optional.of(enfermeroFicticio));
        when(empleadoRepository.findById(cita.getIdEmpleado())).thenReturn(Optional.of(empleadoFicticio));
        when(citaRepository.findByFechaHora(cita.getFechaHora())).thenReturn(null);
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita(1, FECHA_HORA_IDEAL, EstadoCita.REALIZADA, MOTIVO_IDEAL, null, empleadoFicticio, enfermeroFicticio));
        
    }
    /**
     * Verifica que el id del enfermero sea diferente de null
     */
    @Test
    public void csc01(){
        
        // Modifica el id del enfermero
        cita.setIdEnfermero(null);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Enfermero requerido."));
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que el id del empleado sea diferente de null
     */
    @Test
    public void csc02(){
        // Modifica el id del empleado
        cita.setIdEmpleado(null);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Empleado requerido."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que el id del enfermero sea mayor a cero.
     */
    @Test
    public void csc03(){
        // Modifica el id del enfermero
        cita.setIdEnfermero(0);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Enfermero requerido."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que el id del empleado sea mayor a cero.
     */
    @Test
    public void csc04(){
        // Modifica el id del enfermero
        cita.setIdEmpleado(0);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Empleado requerido."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que el id del enfermero sea mayor a cero.
     */
    @Test
    public void csc05(){
        // Modifica el id del enfermero
        cita.setIdEnfermero(-1);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Enfermero requerido."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que el id del empleado sea mayor a cero.
     */
    @Test
    public void csc06(){
        // Modifica el id del enfermero
        cita.setIdEmpleado(-1);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Empleado requerido."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    /**
     * Verifica que el motivo sea diferente de null.
     */
    @Test
    public void csc07(){
        // Modifica el motivo de la cita
        cita.setMotivo(null);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Motivo requerido."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    
    /**
     * Verifica que el motivo no esté vacío.
     */
    @Test
    public void csc08(){
        // Modifica el motivo de la cita
        cita.setMotivo("");
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Motivo requerido."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    
    /**
     * Verifica que el motivo no esté vacío (espacios en blanco).
     */
    @Test
    public void csc09(){
        // Modifica el motivo de la cita
        cita.setMotivo("  ");
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Motivo requerido."));
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(empleadoRepository);
        verifyNoInteractions(enfermeroRepository);
        verifyNoInteractions(citaRepository);
    }
    
    /**
     * Verifica que la fecha no sea null
     */
    @Test
    public void csc10(){
        // Modifica el minuto de la fecha y hora de la cita
        LocalDateTime fechaHoraInvalida = null;
        // Asigna la fecha y hora inválida a la cita
        cita.setFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Fecha y hora requerida."));
    }
    
    /**
     * Verifica que el minuto de la fecha y hora de la cita sea múltiplo de 15.
     */
    @Test
    public void csc11(){
        
        // Calcula una hora que no sea múltiplo de la duración de la cita
        int horaInvalida = FECHA_HORA_IDEAL.getMinute();
        while(horaInvalida % HorarioEnfermero.DURACION_CITA == 0 && horaInvalida < 60)
            horaInvalida++;
        
        // Modifica el minuto de la fecha y hora de la cita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                FECHA_HORA_IDEAL.toLocalDate(), 
                LocalTime.of(FECHA_HORA_IDEAL.getHour(), horaInvalida, FECHA_HORA_IDEAL.getSecond())
        );
        // Asigna la fecha y hora inválida a la cita
        cita.setFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Cada cita debe tener una duración de 15 minutos"));
    }
    /**
     * Verifica que el segundo de la fecha y hora de la cita sea igual a cero.
     */
    @Test
    public void csc12(){
        // Modifica el segundo de la fecha y hora de la cita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                FECHA_HORA_IDEAL.toLocalDate(), 
                LocalTime.of(FECHA_HORA_IDEAL.getHour(), FECHA_HORA_IDEAL.getMinute(), 57)
        );
        // Asigna la fecha y hora inválida a la cita
        cita.setFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Cada cita debe tener una hora exacta."));
    }
    /**
     * Verifica que la fecha y hora de la cita sea mayor o igual que la de entrada 
     * del enfermero (08:00:00 a.m).
     */
    @Test
    public void csc13(){
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
                FECHA_HORA_IDEAL.toLocalDate(),
                horaInvalida // Se asigna la hora inválida
        );
        
        // Se verifica que la fecha a probar esté dentro del horario del enfermero y esté en el futuro
        while(!HorarioEnfermero.diasLaborales.contains(fechaHoraInvalida.getDayOfWeek()) || fechaHoraInvalida.isBefore(LocalDateTime.now()))
            fechaHoraInvalida = fechaHoraInvalida.plusDays(1);
        
        // Asigna la fecha y hora inválida a la cita
        cita.setFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La cita no puede estar antes del período de citas."));
    }
    /**
     * Verifica que la fecha y hora de la cita sea menor que la de salida
     * del enfermero (04:00:00 p.m).
     */
    @Test
    public void csc14(){
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
                FECHA_HORA_IDEAL.toLocalDate(), 
                horaInvalida // Se suma una hora a la hora de termino de citas y se asigna el resultado
        );
        // Asigna la fecha y hora inválida a la cita
        cita.setFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
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
    public void csc15(){
        // Resta los días de diferencia de la fecha ideal respecto a la actual
        LocalDateTime fechaHoraInvalida = FECHA_HORA_IDEAL.minusDays(diasTranscurridos);
        // Asigna la fecha y hora inválida a la cita
        cita.setFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
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
     * ejecute en un minuto múltiplo de 15: La prueba se puede
     * ejecutar únicamente en un lapso de 1 minuto.
     */
    @Test
    public void csc16(){
        // Obtiene la fecha y hora actual (el segundo se establece a cero)
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute())
        );
        // Asigna la fecha y hora inválida a la cita
        cita.setFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
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
    public void csc17(){
        // Establece el día de la fecha en un domingo
        LocalDateTime fechaHoraInvalida = FECHA_HORA_IDEAL;
        while(!fechaHoraInvalida.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            fechaHoraInvalida = fechaHoraInvalida.plusDays(1);
        // Asigna la fecha y hora inválida a la cita
        cita.setFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La cita está fuera del horario laboral del enfermero."));
    }
    /**
     * Verifica que se permita el registro de la cita si todos los datos
     * son válidos.
     */
    @Test
    public void csc18(){
        // Verifica que no se lance ninguna excepción
        assertDoesNotThrow(() -> {servicioCitas.crear(cita);});
        // Verifica si se llamó exactamente una vez el método save() del repositorio de citas
        verify(citaRepository, times(1)).save(any(Cita.class));
    }
}