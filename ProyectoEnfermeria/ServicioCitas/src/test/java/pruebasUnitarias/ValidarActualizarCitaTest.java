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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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
public class ValidarActualizarCitaTest {
    
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
    private static final ActualizarCitaRequest nuevaCita = new ActualizarCitaRequest();
    
    // Cita original ficticia
    private static Cita citaOriginal;
    
    @BeforeAll
    public static void setup(){
        // Establece la fecha ideal como la de hoy a las 3:30 p.m.
        NUEVA_FECHA_HORA_IDEAL = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30, 0)).plusDays(7);
        // Se verifica que la fecha a probar esté dentro del horario del enfermero y esté en el futuro
        while(!HorarioEnfermero.diasLaborales.contains(NUEVA_FECHA_HORA_IDEAL.getDayOfWeek()) || NUEVA_FECHA_HORA_IDEAL.minusDays(7).isBefore(LocalDateTime.now()))
            NUEVA_FECHA_HORA_IDEAL = NUEVA_FECHA_HORA_IDEAL.plusDays(1);
        
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
        System.out.printf("Cita ideal reagendada: %d/%d/%d : %d:%d:%d \n", 
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
    }
    
    @BeforeEach
    public void cargarNuevaCita(){
        // Establece los atributos ideales a nuevaCita.
        nuevaCita.setIdCita(ID_CITA_IDEAL);
        nuevaCita.setNuevaFechaHora(NUEVA_FECHA_HORA_IDEAL);
    }
    
    /**
     * Valida que no se pueda actualizar una cita si el id de la cita
     * asociado a la solicitud no corresponde a una cita registrada.
     */
    @Test
    public void rcsc01(){
        // Omite la validación de la nueva fecha y hora
        when(citaRepository.findByFechaHora(nuevaCita.getNuevaFechaHora())).thenReturn(null);
        
        // Cuando se busque la cita original, a partir del id de la cita asociado a la solicitud, se regresará un objeto Optional vacío
        when(citaRepository.findById(nuevaCita.getIdCita())).thenReturn(Optional.empty());
        
        // Si se intenta actualizar la cita, se devolverá una cita vacía
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La cita no existe."));
        
        // Verifica que el método findById haya sido llamado una vez
        verify(citaRepository, times(1)).findById(anyInt());
        // Verifica que no se haya llamado al método save()
        verify(citaRepository, never()).save(any(Cita.class));
    }
    /**
     * Valida que no se pueda actualizar una cita si la nueva
     * fecha y hora corresponde a otra cita existente.
     */
    @Test
    public void rcsc02(){
         // Omite la validación de la búsqueda de la cita por id
         when(citaRepository.findById(nuevaCita.getIdCita())).thenReturn(Optional.of(citaOriginal));
         
         // Cita existente ficticia
         Cita citaExistente = new Cita(
                 2, 
                 NUEVA_FECHA_HORA_IDEAL,
                 EstadoCita.PENDIENTE,
                 "Chequeo general",
                 null,
                 new Empleado(),
                 new Enfermero()
         );
        // Cuando se busque una cita a partir de la fecha y hora de la nueva fecha y hora, retornará una cita existente
        when(citaRepository.findByFechaHora(nuevaCita.getNuevaFechaHora())).thenReturn(Optional.of(citaExistente));
         
        // Si se intenta actualizar la cita, se devolverá una cita vacía
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("Ya existe una cita con la misma fecha y hora."));
        
        // Verifica que el método findByFechaHora haya sido llamado una vez
        verify(citaRepository, times(1)).findByFechaHora(any(LocalDateTime.class));
        // Verifica que no se haya llamado al método save()
        verify(citaRepository, never()).save(any(Cita.class));
    }
    /**
     * Valida que no se pueda actualizar una cita si la nueva
     * fecha y hora es igual a la cita original
     */
    @Test
    public void rcsc03(){
        // Establece la fecha de la cita actualizada como la de la original
        nuevaCita.setNuevaFechaHora(citaOriginal.getFechaHora());
        
        // Omite las otras validaciones de negocio
        when(citaRepository.findById(nuevaCita.getIdCita())).thenReturn(Optional.of(citaOriginal));
        when(citaRepository.findByFechaHora(nuevaCita.getNuevaFechaHora())).thenReturn(Optional.of(citaOriginal));
         
        // Si se intenta actualizar la cita, se devolverá una cita vacía
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La nueva fecha es la misma que la original."));
        
        // Verifica que el método findById haya sido llamado una vez
        verify(citaRepository, times(1)).findById(anyInt());
        // Verifica que no se haya llamado al método save()
        verify(citaRepository, never()).save(any(Cita.class));
    }
    /**
     * Valida que no se pueda actualizar una cita si la nueva
     * fecha y hora está ubicada antes que la cita original.
     */
    @Test
    public void rcsc04(){
        // Voltea las fechas de la cita original y la actualizada
        nuevaCita.setNuevaFechaHora(citaOriginal.getFechaHora());
        Cita citaOriginalFutura = new Cita(
                citaOriginal.getIdCita(), 
                NUEVA_FECHA_HORA_IDEAL, // Fecha y hora futura
                citaOriginal.getEstado(),
                citaOriginal.getMotivo(), 
                citaOriginal.getSerie(), 
                citaOriginal.getEmpleado(), 
                citaOriginal.getEnfermero()
        );
        
        // Cuando se busque la cita original por su ID, retornará la cita original con la fecha futura
        when(citaRepository.findById(nuevaCita.getIdCita())).thenReturn(Optional.of(citaOriginalFutura));
        
        // Omite la otra validación de negocio
        when(citaRepository.findByFechaHora(nuevaCita.getNuevaFechaHora())).thenReturn(Optional.empty());
        
        // Si se intenta actualizar la cita, se devolverá una cita vacía
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La nueva fecha está ubicada antes de la cita original."));
        
        // Verifica que el método findById haya sido llamado una vez
        verify(citaRepository, times(1)).findById(anyInt());
        // Verifica que no se haya llamado al método save()
        verify(citaRepository, never()).save(any(Cita.class));
    }
    /**
     * Valida que no se pueda actualizar una cita si la cita
     * original no está pendiente.
     */
    @Test
    public void rcsc05(){
        // Crea una cita original con un estado incorrecto
        Cita citaOriginalNoPendiente = new Cita(
                citaOriginal.getIdCita(), 
                citaOriginal.getFechaHora(), 
                EstadoCita.CANCELADA, // Estado diferente de PENDIENTE
                citaOriginal.getMotivo(), 
                citaOriginal.getSerie(), 
                citaOriginal.getEmpleado(), 
                citaOriginal.getEnfermero()
        );
        // Cuando se busque la cita original por su ID, retornará la cita con estado inválido
        when(citaRepository.findById(nuevaCita.getIdCita())).thenReturn(Optional.of(citaOriginalNoPendiente));
        
        // Omite la otra validación de negocio
        when(citaRepository.findByFechaHora(nuevaCita.getNuevaFechaHora())).thenReturn(Optional.empty());
        
        // Si se intenta actualizar la cita, se devolverá una cita vacía
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
        
        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.actualizar(nuevaCita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("La cita original no está pendiente. No se puede reagendar."));
        
        // Verifica que el método findById haya sido llamado una vez
        verify(citaRepository, times(1)).findById(anyInt());
        // Verifica que no se haya llamado al método save()
        verify(citaRepository, never()).save(any(Cita.class));
    }
    
     /**
     * Valida que se actualice la cita si se han cumplido todas las validaciones
     * de negocio correspondientes.
     */
     @Test
     public void rcsc06(){
        // Pasa las validaciones de negocio de los dos parámetros de la solicitud
        when(citaRepository.findById(nuevaCita.getIdCita())).thenReturn(Optional.of(citaOriginal));
        when(citaRepository.findByFechaHora(nuevaCita.getNuevaFechaHora())).thenReturn(Optional.empty());
        
        // Si se intenta registrar la nueva cita, devolverá una Cita con los datos de la nueva cita y un nuevo ID
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita(
                1, 
                NUEVA_FECHA_HORA_IDEAL, 
                citaOriginal.getEstado(), 
                citaOriginal.getMotivo(), 
                citaOriginal.getSerie(), 
                citaOriginal.getEmpleado(), 
                citaOriginal.getEnfermero())
        );
        
        // Verifica que no se lance ninguna excepción
        assertDoesNotThrow(() -> {servicioCitas.actualizar(nuevaCita);});
        
        // Verifica que el método findById haya sido llamado una vez
        verify(citaRepository, times(1)).findById(anyInt());
        // Verifica que el método findByFechaHora haya sido llamado una vez
        verify(citaRepository, times(1)).findByFechaHora(any(LocalDateTime.class));
        // Verifica si se llamó exactamente una vez el método save() del repositorio de citas
        verify(citaRepository, times(1)).save(any(Cita.class));
        // Verifica que no se haya llamado a más métodos de citaRepository
        verifyNoMoreInteractions(citaRepository);
     }
}