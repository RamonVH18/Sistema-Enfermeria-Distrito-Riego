package pruebasUnitarias;

import repositorios.CitaRepository;
import repositorios.EmpleadoRepository;
import repositorios.EnfermeroRepository;
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
import servicios.ServicioCitas;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
@ExtendWith(SpringExtension.class) // Levanta el contexto de SpringBoot = obligatorio
@Import({
    ServicioCitas.class, // Importante incluir la clase que implementa la interfaz de IServicioCitas
    MethodValidationPostProcessor.class // IMPORTANTE INCLUIR ESTA CLASE PARA ACTIVAR LAS ETIQUETAS DE VALIDACIÓN
})
public class ValidarCancelarCitaTest {
//  
//    // Se mockean los repositorios (la base de datos)
//    @MockitoBean
//    private CitaRepository citaRepository;
//    @MockitoBean
//    private EmpleadoRepository empleadoRepository;
//    @MockitoBean
//    private EnfermeroRepository enfermeroRepository;
//    
//    // Se inyectan los repositorios mockeados al servicio de citas
//    @Autowired
//    private IServicioCitas servicioCitas;
//    
//    // Id ideal de la cita a cancelar
//    private static final Integer ID_CITA_IDEAL = 1;
//    
//    // Cita original ficticia
//    private static Cita citaOriginal;
//    
//    @BeforeAll
//    public static void setUp(){
//        // Establece la fecha ideal como la de hoy a las 3:30 p.m.
//        LocalDateTime fechaHoraIdeal = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30, 0));
//        // Se verifica que la fecha a probar esté dentro del horario del enfermero y esté en el futuro
//        while(!HorarioEnfermero.diasLaborales.contains(fechaHoraIdeal.getDayOfWeek()) || fechaHoraIdeal.isBefore(LocalDateTime.now()))
//            fechaHoraIdeal = fechaHoraIdeal.plusDays(1);
//                
//        citaOriginal = new Cita(
//                ID_CITA_IDEAL,
//                fechaHoraIdeal.plusDays(7),
//                EstadoCita.PENDIENTE,
//                "Chequeo general",
//                null,
//                new Empleado(
//                        1, "Leonardo ", "Flores", "Leyva",
//                        LocalDate.of(1991, Month.JULY, 3),
//                        "6441549274",
//                        "GAMC850820HDFRRR05",
//                        "Obregón",
//                        "Masculino",
//                        EstadoEmpleado.ACTIVO,
//                        new Departamento(1, "Contaduría"),
//                        new DireccionEmpleado(20, "Miguel Hidalgo", "Cajeme", 85286, "Obregón")
//                ),
//                new Enfermero(
//                        2,
//                        new Empleado(
//                                2, "Ramón", "Valencia", "Hernández",
//                                LocalDate.of(1984, Month.OCTOBER, 20),
//                                "6442548932",
//                                "RGHE850820HDFRRR09",
//                                "Obregón",
//                                "Pansexual",
//                                EstadoEmpleado.ACTIVO,
//                                new Departamento(2, "Enfermería"),
//                                new DireccionEmpleado(88, "Agustín de Iturbide", "Valle Dorado", 85291, "Obregón")
//                        ),
//                        new ArrayList<>()
//                )
//        );
//        
//        // Indica la fecha de la cita original
//        System.out.printf("Cita ideal a cancelar: %d/%d/%d : %d:%d:%d \n", 
//                citaOriginal.getFechaHora().getDayOfMonth(),
//                citaOriginal.getFechaHora().getMonthValue(),
//                citaOriginal.getFechaHora().getYear(),
//                citaOriginal.getFechaHora().getHour(),
//                citaOriginal.getFechaHora().getMinute(),
//                citaOriginal.getFechaHora().getSecond()
//        );
//    }
//    
//    @BeforeEach
//    public void mock() {
//         // Establece el id ideal de la cita a la solicitud.
//        cancelarCita.setIdCita(ID_CITA_IDEAL);
//    }
//    /**
//     * Valida que no se pueda cancelar una cita si el id de la cita
//     * asociado a la solicitud no corresponde a una cita registrada.
//     */
//    @Test
//    public void ccsc01(){
//        // Cuando se intente buscar la cita por su ID, devolverá un objeto de la clase Optional vacío
//        when(citaRepository.findById(ID_CITA_IDEAL)).thenReturn(Optional.empty());
//        
//        // Si se intenta actualizar la cita, se devolverá una cita vacía
//        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
//        
//        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
//        Exception error = assertThrows(Exception.class, () -> {servicioCitas.cancelar(cancelarCita);});
//        /*
//            Verifica que el mensaje de la excepción corresponda con el error identificado
//            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
//            fue actualizado.
//        */
//        assertTrue(error.getMessage().contains("La cita no existe."));
//        
//        // Verifica que el método findById haya sido llamado una vez
//        verify(citaRepository, times(1)).findById(anyInt());
//        // Verifica que no se haya llamado al método save()
//        verify(citaRepository, never()).save(any(Cita.class));
//    }
//    @Test
//    public void ccsc02(){
//        // Crea una cita original con un estado incorrecto
//        Cita citaOriginalNoPendiente = new Cita(
//                citaOriginal.getIdCita(), 
//                citaOriginal.getFechaHora(), 
//                EstadoCita.CANCELADA, // Estado diferente de PENDIENTE
//                citaOriginal.getMotivo(), 
//                citaOriginal.getSerie(), 
//                citaOriginal.getEmpleado(), 
//                citaOriginal.getEnfermero()
//        );
//        
//        // Cuando se busque la cita original por su ID, retornará la cita con estado inválido
//        when(citaRepository.findById(ID_CITA_IDEAL)).thenReturn(Optional.of(citaOriginalNoPendiente));
//        
//        // Si se intenta actualizar la cita, se devolverá una cita vacía
//        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita());
//        
//        // Ejecuta el método "actualizar" del servicio y verifica que se lanzó una excepción
//        Exception error = assertThrows(Exception.class, () -> {servicioCitas.cancelar(cancelarCita);});
//        /*
//            Verifica que el mensaje de la excepción corresponda con el error identificado
//            Se recomienda actualizar el mensaje si el mensaje original de la excepción 
//            fue actualizado.
//        */
//        assertTrue(error.getMessage().contains("La cita no está pendiente. No se puede cancelar."));
//        
//        // Verifica que el método findById haya sido llamado una vez
//        verify(citaRepository, times(1)).findById(anyInt());
//        // Verifica que no se haya llamado al método save()
//        verify(citaRepository, never()).save(any(Cita.class));
//    }
//    /**
//     * Valida que se cancele la cita si se han cumplido todas las validaciones
//     * de negocio correspondientes.
//     */
//    @Test
//    public void ccsc03(){
//        // Cuando se intente buscar la cita por su ID, devolverá la cita original
//        when(citaRepository.findById(ID_CITA_IDEAL)).thenReturn(Optional.of(citaOriginal));
//        // Si se intenta cancelar la cita original, devolverá una Cita con los datos de la nueva cita y su estado actualizado como CANCELADA
//        when(citaRepository.save(any(Cita.class))).thenReturn(new Cita(
//                ID_CITA_IDEAL, 
//                citaOriginal.getFechaHora(), 
//                EstadoCita.CANCELADA, 
//                citaOriginal.getMotivo(), 
//                citaOriginal.getSerie(), 
//                citaOriginal.getEmpleado(), 
//                citaOriginal.getEnfermero())
//        );
//        
//        // Verifica que no se lance ninguna excepción
//        assertDoesNotThrow(() -> {servicioCitas.cancelar(ID_CITA_IDEAL);});
//        
//        // Verifica que el método findById haya sido llamado una vez
//        verify(citaRepository, times(1)).findById(anyInt());
//        // Verifica si se llamó exactamente una vez el método save() del repositorio de citas
//        verify(citaRepository, times(1)).save(any(Cita.class));
//        // Verifica que no se haya llamado a más métodos de citaRepository
//        verifyNoMoreInteractions(citaRepository);
//    }
}