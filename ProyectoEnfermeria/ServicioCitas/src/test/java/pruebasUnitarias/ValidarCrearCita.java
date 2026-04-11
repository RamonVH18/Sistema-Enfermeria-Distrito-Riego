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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
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
import request.CrearCitaRequest;
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
public class ValidarCrearCita {
    
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
    private static final Integer ID_EMPLEADO_IDEAL = 1;
    private static final Integer ID_ENFERMERO_IDEAL = 2;
    private static LocalDateTime FECHA_HORA_IDEAL;
    private static final String MOTIVO_IDEAL = "Chequeo general";
    
    // Petición a validar
    private static final CrearCitaRequest cita = new CrearCitaRequest();
    
    // Empleado ficticio
    private final Empleado empleadoFicticio = new Empleado(
            ID_EMPLEADO_IDEAL, 
            "Leonardo ", "Flores", "Leyva",
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
    private final Enfermero enfermeroFicticio = new Enfermero(
            ID_ENFERMERO_IDEAL, 
            new Empleado(
                ID_ENFERMERO_IDEAL, 
                "Ramón", "Valencia", "Hernández",
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
    public static void setUpClass() {
        // Establece la fecha ideal como la de hoy a las 3:30 p.m.
        FECHA_HORA_IDEAL = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30, 0));
        // Se verifica que la fecha a probar esté dentro del horario del enfermero y esté en el futuro
        while(!HorarioEnfermero.diasLaborales.contains(FECHA_HORA_IDEAL.getDayOfWeek()) || FECHA_HORA_IDEAL.isBefore(LocalDateTime.now()))
            FECHA_HORA_IDEAL = FECHA_HORA_IDEAL.plusDays(1);
        
        // Indica la fecha a probar
        System.out.printf("Cita ideal: %d/%d/%d : %d:%d:%d \n", 
                FECHA_HORA_IDEAL.getDayOfMonth(),
                FECHA_HORA_IDEAL.getMonthValue(),
                FECHA_HORA_IDEAL.getYear(),
                FECHA_HORA_IDEAL.getHour(),
                FECHA_HORA_IDEAL.getMinute(),
                FECHA_HORA_IDEAL.getSecond()
        );
    }
    
    @BeforeEach
    public void cargarCita(){
        // Establece los atributos ideales a la cita.
        cita.setIdEmpleado(ID_EMPLEADO_IDEAL);
        cita.setIdEnfermero(ID_ENFERMERO_IDEAL);
        cita.setFechaHora(FECHA_HORA_IDEAL);
        cita.setMotivo(MOTIVO_IDEAL);
    }
    
    /**
     * Valida que una nueva cita no pueda agendarse en la 
     * misma fecha y hora que una previamente registrada.
     */
    @Test
    public void ncsc01(){
        // Cita existente
        Cita citaExistente = new Cita(1, FECHA_HORA_IDEAL, EstadoCita.PENDIENTE, MOTIVO_IDEAL, null, empleadoFicticio, enfermeroFicticio);
        
        // Omite las validaciones de negocio de empleadoRepository y enfermeroRepository
        when(enfermeroRepository.findById(cita.getIdEnfermero())).thenReturn(Optional.of(enfermeroFicticio));
        when(empleadoRepository.findById(cita.getIdEmpleado())).thenReturn(Optional.of(empleadoFicticio));
        
        // Cuando se busque la cita por su fecha y hora, deberá retornar una cita existente
        when(citaRepository.findByFechaHora(cita.getFechaHora())).thenReturn(citaExistente);
        
        // Si se intenta registrar la nueva cita, devolverá una Cita vacía
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
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
     * Valida que una nueva cita no pueda agendarse con un id
     * del empleado que no corresponde a un empleado real.
     */
    @Test
    public void ncsc02(){
               
        // Omite las validaciones de negocio del enfermero y de la fecha y hora
        when(enfermeroRepository.findById(cita.getIdEnfermero())).thenReturn(Optional.of(enfermeroFicticio));
        when(citaRepository.findByFechaHora(cita.getFechaHora())).thenReturn(null);
        
        // Cuando se busque al empleado por su id, devolverá un objeto de la clase Optional vacío
        when(empleadoRepository.findById(cita.getIdEmpleado())).thenReturn(Optional.empty());
        
        // Si se intenta registrar la nueva cita, devolverá una Cita vacía
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("No existe un empleado asociado a la cita."));
        
        // Verifica que el método findById de empleadoRepository haya sido llamado una vez
        verify(empleadoRepository, times(1)).findById(anyInt());
        // Verifica que no se haya llamado al método save()
        verify(citaRepository, never()).save(any(Cita.class));
    }
    /**
     * Valida que una nueva cita no pueda agendarse con un id
     * del enfermero que no corresponde a un enfermero real.
     */
    @Test
    public void ncsc03(){
               
        // Omite las validaciones de negocio del empleado y de la fecha y hora
        when(empleadoRepository.findById(cita.getIdEmpleado())).thenReturn(Optional.of(empleadoFicticio));
        when(citaRepository.findByFechaHora(cita.getFechaHora())).thenReturn(null);
        
        // Cuando se busque al enfermero por su id, devolverá un objeto de la clase Optional vacío
        when(enfermeroRepository.findById(cita.getIdEnfermero())).thenReturn(Optional.empty());
        
        // Si se intenta registrar la nueva cita, devolverá una Cita vacía
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
        
        // Ejecuta el método "crear" del servicio y verifica que se lanzó una excepción
        Exception error = assertThrows(Exception.class, () -> {servicioCitas.crear(cita);});
        /*
            Verifica que el mensaje de la excepción corresponda con el error identificado
            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
            fue actualizado.
        */
        assertTrue(error.getMessage().contains("No existe un enfermero asociado a la cita."));
        
        // Verifica que el método findById de empleadoRepository haya sido llamado una vez
        verify(enfermeroRepository, times(1)).findById(anyInt());
        // Verifica que no se haya llamado al método save()
        verify(citaRepository, never()).save(any(Cita.class));
    }
    /**
     * Valida que se registre la cita si se han cumplido todas las validaciones
     * de negocio correspondientes.
     */
    @Test
    public void ncsc04(){
        // Pasa las validaciones de negocio de los tres parámetros de la solicitud
        when(empleadoRepository.findById(cita.getIdEmpleado())).thenReturn(Optional.of(empleadoFicticio));
        when(enfermeroRepository.findById(cita.getIdEnfermero())).thenReturn(Optional.of(enfermeroFicticio));
        when(citaRepository.findByFechaHora(cita.getFechaHora())).thenReturn(null);
        
        // Si se intenta registrar la nueva cita, devolverá una Cita con los datos de la nueva cita y un nuevo ID
        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita(
                1, // Nuevo ID
                FECHA_HORA_IDEAL, 
                EstadoCita.PENDIENTE, 
                MOTIVO_IDEAL, 
                null, 
                empleadoFicticio, 
                enfermeroFicticio
        ));
        // Verifica que no se lance ninguna excepción
        assertDoesNotThrow(() -> {servicioCitas.crear(cita);});
        
        // Verifica si se llamó exactamente una vez el método findById() del repositorio de empleados
        verify(empleadoRepository, times(1)).findById(anyInt());
        // Verifica si se llamó exactamente una vez el método findById() del repositorio de enfermeros
        verify(enfermeroRepository, times(1)).findById(anyInt());
        // Verifica si se llamó exactamente una vez el método findByFechaHora() del repositorio de citas
        verify(citaRepository, times(1)).findByFechaHora(any(LocalDateTime.class));
        // Verifica si se llamó exactamente una vez el método save() del repositorio de citas
        verify(citaRepository, times(1)).save(any(Cita.class));
        
        // Verifica que ya no se llamó a ningún otro método de cada repositorio
        verifyNoMoreInteractions(citaRepository);
        verifyNoMoreInteractions(empleadoRepository);
        verifyNoMoreInteractions(enfermeroRepository);
    }
}