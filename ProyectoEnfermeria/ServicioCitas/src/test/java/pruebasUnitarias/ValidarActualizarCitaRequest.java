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
@ExtendWith(SpringExtension.class) 
@Import({ServicioCitas.class, MethodValidationPostProcessor.class})
public class ValidarActualizarCitaRequest {
    
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
        ¡Ojo! El valor de NUEVA_FECHA_HORA_IDEAL está sujeto al tiempo. Si no se desea actualizar
        su valor en cada prueba, se puede codificar para que se ajuste a partir de la fecha
        actual. En esta primera versión, las pruebas están contempladas para ser ejecutadas
        el 14 de abril del 2026. Si ya realizó esta actualización, puede eliminar este
        comentario.
    */
    private static final Integer ID_CITA_IDEAL = 1;
    private static final LocalDateTime NUEVA_FECHA_HORA_IDEAL = LocalDateTime.of(2026, Month.APRIL, 10, 15, 45, 0);
    
    // Solicitud a validar
    ActualizarCitaRequest nuevaCita = new ActualizarCitaRequest();
    
    // Cita original ficticia
    Cita citaOriginal = new Cita(
            ID_CITA_IDEAL,
            NUEVA_FECHA_HORA_IDEAL.minusDays(7),
            EstadoCita.PENDIENTE,
            "Chequeo general",
            null,
            new Empleado(
                    1,
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
            ),
            new Enfermero(
                    2,
                    new Empleado(
                            2,
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
            )
    );
    
    /**
     * Establece los atributos de la nueva nuevaCita con los ideales antes
     * de cada prueba con el objetivo de no repetir tanto código.
     */
    @BeforeEach
    public void setup(){
        // Establece los atributos ideales a la nueva nuevaCita.
        nuevaCita.setIdCita(ID_CITA_IDEAL);
        nuevaCita.setNuevaFechaHora(NUEVA_FECHA_HORA_IDEAL);
        // Omite las validaciones de negocio
        when(citaRepository.findById(nuevaCita.getIdCita())).thenReturn(Optional.of(citaOriginal));
        when(citaRepository.findByFechaHora(nuevaCita.getNuevaFechaHora())).thenReturn(null);
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
        
        // Modifica el id del enfermero
        nuevaCita.setIdCita(null);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        
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
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        
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
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        
        // Verifica que no se llamó a ningún método de los repositorios
        verifyNoInteractions(citaRepository);
    }
    
    /**
     * Verifica que el minuto de la fecha y hora de la nuevaCita sea múltiplo de 15.
     */
    @Test
    public void asc04(){
        // Modifica el minuto de la fecha y hora de la nuevaCita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                NUEVA_FECHA_HORA_IDEAL.toLocalDate(), 
                LocalTime.of(NUEVA_FECHA_HORA_IDEAL.getHour(), 1, NUEVA_FECHA_HORA_IDEAL.getSecond())
        );
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
    }
    /**
     * Verifica que el segundo de la fecha y hora de la nuevaCita sea igual a cero.
     */
    @Test
    public void asc05(){
        // Modifica el segundo de la fecha y hora de la nuevaCita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                NUEVA_FECHA_HORA_IDEAL.toLocalDate(), 
                LocalTime.of(NUEVA_FECHA_HORA_IDEAL.getHour(), NUEVA_FECHA_HORA_IDEAL.getMinute(), 57)
        );
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
    }
    /**
     * Verifica que la fecha y hora de la nuevaCita sea mayor o igual que la de entrada 
     * del enfermero (08:00:00 a.m).
     */
    @Test
    public void asc06(){
        // Modifica la hora de la fecha y hora de la nuevaCita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                NUEVA_FECHA_HORA_IDEAL.toLocalDate(), 
                LocalTime.of(2, 0, 0)
        );
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
    }
    /**
     * Verifica que la fecha y hora de la nuevaCita sea menor que la de salida
     * del enfermero (04:00:00 p.m).
     */
    @Test
    public void asc07(){
        // Modifica la hora de la fecha y hora de la nuevaCita
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                NUEVA_FECHA_HORA_IDEAL.toLocalDate(), 
                LocalTime.of(23, 0, 0)
        );
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
    }
    /**
     * Verifica que la fecha y hora esté ubicada en el futuro:
     * No está en el pasado.
     */
    @Test
    public void asc08(){
        // Modifica el día de la fecha y hora de la nuevaCita
        LocalDateTime fechaHoraInvalida = NUEVA_FECHA_HORA_IDEAL.minusDays(1);
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception exception = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        System.out.println(exception.getMessage());
    }
    /**
     * Verifica que la fecha y hora esté ubicada en el futuro:
     * No está en el presente. Se recomienda que la prueba se
     * ejecute en un minuto múltiplo de 15.
     */
    @Test
    public void asc09(){
        // Obtiene la fecha y hora actual (el segundo se establece a cero)
        LocalDateTime fechaHoraInvalida = LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute())
        );
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
    }
    
    /**
     * Verifica que la fecha y hora esté ubicada dentro del horario del
     * enfermero. ¡OJO! Se asume que el enfermero no trabaja los domingos.
     */
    @Test
    public void asc10(){
        // Establece el día de la fecha en un domingo
        LocalDateTime fechaHoraInvalida = NUEVA_FECHA_HORA_IDEAL;
        while(!fechaHoraInvalida.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            fechaHoraInvalida = fechaHoraInvalida.plusDays(1);
        // Asigna la fecha y hora inválida a la nuevaCita
        nuevaCita.setNuevaFechaHora(fechaHoraInvalida);
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
    }
    
    /**
     * Verifica que se permita la actualización de la cita si todos los datos
     * son válidos.
     */
    @Test
    public void asc11(){
        // Verifica que no se lance ninguna excepción
        assertDoesNotThrow(() -> {servicioCitas.actualizar(nuevaCita);});
        // Verifica si se llamó exactamente una vez el método save() del repositorio de citas
        verify(citaRepository, times(1)).save(any(Cita.class));
    }
}