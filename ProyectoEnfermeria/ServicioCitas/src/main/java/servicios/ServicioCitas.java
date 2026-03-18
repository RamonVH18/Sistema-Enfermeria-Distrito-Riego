package servicios;

import DAOs.CitaRepository;
import DAOs.EmpleadoRepository;
import DAOs.EnfermeroRepository;
import dtos.CitaDTO;
import entidades.Cita;
import entidades.Empleado;
import entidades.Enfermero;
import enums.EstadoCita;
import exception.CitasException;
import interfaces.IServicioCitas;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import mapper.CitaMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import request.ActualizarCitaRequest;
import request.CancelarCitaRequest;
import request.CrearCitaRequest;
import response.ActualizarCitaResponse;
import response.CancelarCitaResponse;
import response.CrearCitaResponse;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
@Service
@Validated
public class ServicioCitas implements IServicioCitas{
    
    // Repositorios
    private final CitaRepository citaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EnfermeroRepository enfermeroRepository;
    // Límites de hora y duración de las citas
    private final int DURACION_CITA = 15;
    private final LocalTime HORA_INICIO_CITAS = LocalTime.of(8, 0, 0);
    private final LocalTime HORA_TERMINO_CITAS = LocalTime.of(16, 0, 0);
    // Fomatter de fechas y horas
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy : HH:mm:ss");
    // Logeo del sistema
    private static final System.Logger LOG = System.getLogger(ServicioCitas.class.getName());
    
    /**
     * Constructor con repositorios.
     * @param citaRepository
     * @param empleadoRepository
     * @param enfermeroRepository 
     */
    public ServicioCitas(CitaRepository citaRepository, EmpleadoRepository empleadoRepository, EnfermeroRepository enfermeroRepository) {
        this.citaRepository = citaRepository;
        this.empleadoRepository = empleadoRepository;
        this.enfermeroRepository = enfermeroRepository;
    }
    
    @Override
    public CrearCitaResponse crear(CrearCitaRequest cita) {
        
        // Verificar empleado
        Empleado empleadoAsociado = empleadoRepository.findById(cita.getIdEmpleado()).orElse(null);
        if(empleadoAsociado == null) {throw new CitasException("No existe un empleado asociado a la cita.", HttpStatus.BAD_REQUEST, "400");}
        
        // Verificar al enfermero
        Enfermero enfermeroAsociado = enfermeroRepository.findById(cita.getIdEnfermero()).orElse(null);
        if(enfermeroAsociado == null) {throw new CitasException("No existe un enfermero asociado a la cita.", HttpStatus.BAD_REQUEST, "400");}
        
        // Verifica que la nueva cita no se empalma con una ya existente
        Cita citaMismaFechaHora = citaRepository.findByFechaHora(cita.getFechaHora());
        if(citaMismaFechaHora != null) {throw new CitasException("Ya existe una cita con la misma fecha y hora.", HttpStatus.BAD_REQUEST, "400");}
        
        // Extrae la hora de la cita
        LocalTime horaCita = cita.getFechaHora().toLocalTime();
        // Valida la hora de la cita
        validarHoraCita(horaCita);
        
        // Mapea la solicitud a una entidad
        Cita nuevaCita = CitaMapper.toEntityNew(cita);
        // Marca el estado de la cita como pendiente
        nuevaCita.setEstado(EstadoCita.PENDIENTE);
        // Guarda la cita en la base de datos
        nuevaCita = citaRepository.save(nuevaCita);
        
        // Se extrae los datos de la cita
        // Nombre del empleado
        String nombreEmpleado = empleadoAsociado.getNombres();
        // Fecha y hora de la cita
        LocalDateTime fechaHoraCita = nuevaCita.getFechaHora();
        // Fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Mensaje de la fecha y hora de la cita (formateada)
        String msgFechaHoraCita = fechaHoraCita.format(dateFormat);
        // Mensaje de la fecha y hora actual (formateada)
        String msgFechaHora = fechaHoraActual.format(dateFormat);
        
        // Mensaje a loggear
        String logMsg = String.format("Nueva cita creada exitosamente. Empleado: %s, Cita: %s, Operación: %s", nombreEmpleado, msgFechaHoraCita, msgFechaHora);
        // Loggeo del sistema
        LOG.log(System.Logger.Level.INFO, logMsg);
        
        // Regresa la respuesta
        return new CrearCitaResponse(nombreEmpleado, fechaHoraCita, fechaHoraActual);
    }

    @Override
    public ActualizarCitaResponse actualizar(ActualizarCitaRequest cita){
        
        // Verifica que la nueva fecha y hora corresponda a una cita existente
        Cita citaActualizar = citaRepository.findById(cita.getIdCita()).orElse(null);
        if(citaActualizar == null) {throw new CitasException("La cita no existe.", HttpStatus.BAD_REQUEST, "400");}
        
        // Extrae la hora de la cita
        LocalTime nuevaHoraCita = cita.getNuevaFechaHora().toLocalTime();
        // Valida la hora de la cita
        validarHoraCita(nuevaHoraCita);
        
        // Verifica que la nueva fecha no sea la misma que la cita original
        LocalDateTime nuevaFechaHora = cita.getNuevaFechaHora();
        if(nuevaFechaHora.isEqual(citaActualizar.getFechaHora())) {throw new CitasException("La nueva fecha es la misma que la original.", HttpStatus.BAD_REQUEST, "400");}
        
        // Verifica que no exista otra cita con la misma fecha y hora
        Cita citaMismaFechaHora = citaRepository.findByFechaHora(nuevaFechaHora);
        if(citaMismaFechaHora != null) {throw new CitasException("Ya existe una cita con la misma fecha y hora.", HttpStatus.BAD_REQUEST, "400");}
        
        // Actualiza la fecha y hora de la cita
        citaActualizar.setFechaHora(nuevaFechaHora);
        // Actualiza la cita con la nueva fecha y hora
        citaActualizar = citaRepository.save(citaActualizar);
        
        // Nombre del empleado asociado a la cita
        String nombreEmpleado = citaActualizar.getEmpleado().getNombres();
        // Nueva fecha y hora de la cita
        LocalDateTime nuevaFechaHoraCita = citaActualizar.getFechaHora();
         // Fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Mensaje de la nueva fecha y hora de la cita
        String msgFechaHoraCita = nuevaFechaHoraCita.format(dateFormat);
        // Mensaje de la fecha y hora actual (formateada)
        String msgFechaHora = fechaHoraActual.format(dateFormat);
        
        // Mensaje a loggear
        String logMsg = String.format("Cita reagendada exitosamente. Empleado: %s, Cita: %s, Operación: %s", nombreEmpleado, msgFechaHoraCita, msgFechaHora);
        // Loggeo del sistema
        LOG.log(System.Logger.Level.INFO, logMsg);
        
        // Regresa la respuesta
        return new ActualizarCitaResponse(nombreEmpleado, fechaHoraActual);
    }

    @Override
    public CancelarCitaResponse eliminar(CancelarCitaRequest cita) {
        
        // Verifica que la cita a cancelar exista en la base de datos
        Cita citaEliminar = citaRepository.findById(cita.getIdCita()).orElse(null);
        if(citaEliminar == null) {throw new CitasException("La cita no existe.", HttpStatus.BAD_REQUEST, "400");}
        
        // Actualiza el estado de la cita a CANCELADA
        citaEliminar.setEstado(EstadoCita.CANCELADA);
        // Guarda el cambio de la cita en la base de datos
        citaEliminar = citaRepository.save(citaEliminar);
        
        // Nombre del empleado asociado a la cita
        String nombreEmpleado = citaEliminar.getEmpleado().getNombres();
         // Fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Mensaje de la fecha y hora actual (formateada)
        String msgFechaHora = fechaHoraActual.format(dateFormat);
        
        // Mensaje a loggear
        String logMsg = String.format("Cita cancelada exitosamente. Empleado: %s, Operación: %s", nombreEmpleado, msgFechaHora);
        // Loggeo del sistema
        LOG.log(System.Logger.Level.INFO, logMsg);
        
        // Regresa la respuesta
        return new CancelarCitaResponse(nombreEmpleado, fechaHoraActual);
    }

    @Override
    public List<CitaDTO> obtenerTodas() {
        // Obtiene todas las citas
        List<Cita> citasEncontradas = citaRepository.findAll();
        // Si la lista no está vacía
        if(!citasEncontradas.isEmpty()){
            // Mapea las citas a DTOs
            List<CitaDTO> citasEncontradasDTO = CitaMapper.toDTOList(citasEncontradas);
            // Regresa la lista de citas mapeadas
            return citasEncontradasDTO;
        // Regresa null si no se encontraron citas
        } else { return null; }
    }

    @Override
    public CitaDTO obtenerPorId(Integer id) {
        // Consulta la cita y la guarda en un objeto Optional
        Optional<Cita> resultado = citaRepository.findById(id);
        // Extrae la cita (regresa null si no se obtuvo)
        Cita citaEncontrada = resultado.orElse(null);
        // Regresa la cita mapeada a DTO
        return (citaEncontrada != null) ? CitaMapper.toDTO(citaEncontrada) : null;
    }

    @Override
    public List<CitaDTO> obtenerPorFechaPendiente(LocalDate fecha) {
        // Verifica que la fecha no sea null
        if(fecha == null){ return null; }
        else{
            // Lista de citas encontradas
            List<CitaDTO> citasEncontradasDTO;
            // Límites (mismo día)
            LocalDateTime inicio = LocalDateTime.of(fecha, LocalTime.of(0, 0, 0));
            LocalDateTime fin = LocalDateTime.of(fecha, LocalTime.of(23, 59, 59));
            // Ejecuta la consulta
            List<Cita> citasEncontradas = citaRepository.findByFechaHoraBetweenAndEstado(inicio, fin, EstadoCita.PENDIENTE);
            // Si la lista de citas no está vacía
            if (citasEncontradas != null && !citasEncontradas.isEmpty()){
                // Mapea las citas encontradas a DTOs
                citasEncontradasDTO = CitaMapper.toDTOList(citasEncontradas);
                // Regresa la lista de citas
                return citasEncontradasDTO;
            // Regresa null si no se encontraron citas
            } else{ return null; }
        }
    }

    @Override
    public List<CitaDTO> buscarPorNombreCurpPacientePendiente(String nombreCurp) {
        // Verifica que la fecha no sea null
        if(nombreCurp == null){ return null; }
        else{
            // Lista de citas encontradas
            List<CitaDTO> citasEncontradasDTO;
            // Ejecuta la consulta
            List<Cita> citasEncontradas = citaRepository.findByNombreOrCurpPendiente(nombreCurp, EstadoCita.PENDIENTE);
            // Si la lista de citas no está vacía
            if (citasEncontradas != null && !citasEncontradas.isEmpty()){
                // Mapea las citas encontradas a DTOs
                citasEncontradasDTO = CitaMapper.toDTOList(citasEncontradas);
                // Regresa la lista de citas
                return citasEncontradasDTO;
            // Regresa null si no se encontraron citas
            } else{ return null; }
        }
    }
    
    /**
     * Valida la hora de una cita.
     * @param horaCita Hora de la cita.
     */
    private void validarHoraCita(LocalTime horaCita){
        // Verifica que el minuto es múltiplo de la duración de la cita.
        if(horaCita.getMinute() % DURACION_CITA != 0) {throw new CitasException(String.format("Cada cita debe tener una duración de %d minutos", DURACION_CITA), HttpStatus.BAD_REQUEST, "400");}
        // Verifica que el segundo de la hora es igual a cero.
        if(horaCita.getSecond() != 0) {throw new CitasException("Cada cita debe tener una hora exacta.", HttpStatus.BAD_REQUEST, "400");}
        // Verifica que la hora de la cita no es inferior a la hora de comienzo de citas
        if(horaCita.isBefore(HORA_INICIO_CITAS)) {throw new CitasException("La cita no puede estar antes del período de citas.", HttpStatus.BAD_REQUEST, "400");}
        // Verifica que la hora de la cita no es superior a la hora de término de citas
        if(horaCita.isAfter(HORA_TERMINO_CITAS)) {throw new CitasException("La cita no puede estar después del período de citas.", HttpStatus.BAD_REQUEST, "400");}
    }
}