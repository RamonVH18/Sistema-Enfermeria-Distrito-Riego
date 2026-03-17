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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    
    private final CitaRepository citaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EnfermeroRepository enfermeroRepository;

    public ServicioCitas(CitaRepository citaRepository, EmpleadoRepository empleadoRepository, EnfermeroRepository enfermeroRepository) {
        this.citaRepository = citaRepository;
        this.empleadoRepository = empleadoRepository;
        this.enfermeroRepository = enfermeroRepository;
    }
    
    @Override
    public CrearCitaResponse crear(@Valid CrearCitaRequest cita) {
        
        // Verificar empleado
        Empleado empleadoAsociado = empleadoRepository.findById(cita.getIdEmpleado()).orElse(null);
        if(empleadoAsociado == null)
            throw new CitasException("No existe un empleado asociado a la cita.", HttpStatus.BAD_REQUEST, "400");
        
        // Verificar al enfermero
        Enfermero enfermeroAsociado = enfermeroRepository.findById(cita.getIdEnfermero()).orElse(null);
        if(enfermeroAsociado == null)
            throw new CitasException("No existe un enfermero asociado a la cita.", HttpStatus.BAD_REQUEST, "400");
        
        // Verifica que la nueva cita no se empalma con una ya existente
        Cita citaMismaFechaHora = citaRepository.findByFechaHora(cita.getFechaHora());
        if(citaMismaFechaHora != null)
            throw new CitasException("Ya existe una cita con la misma fecha y hora.", HttpStatus.BAD_REQUEST, "400");
        
        // Mapea la solicitud a una entidad
        Cita nuevaCita = CitaMapper.toEntityNew(cita);
        
        // Marca el estado de la cita como pendiente
        nuevaCita.setEstado(EstadoCita.PENDIENTE);
        
        // Guarda la cita en la base de datos
        nuevaCita = citaRepository.save(nuevaCita);
        
        // Regresa la respuesta
        return new CrearCitaResponse(
                nuevaCita.getEmpleado().getNombres(), 
                nuevaCita.getFechaHora(), 
                LocalDateTime.now()
        );
    }

    @Override
    public ActualizarCitaResponse actualizar(@Valid ActualizarCitaRequest cita){
        
        // Verifica que la nueva fecha y hora corresponda a una cita existente
        Cita citaActualizar = citaRepository.findById(cita.getIdCita()).orElse(null);
        if(citaActualizar == null)
            throw new CitasException("La cita no existe.", HttpStatus.BAD_REQUEST, "400");
        
        // Verifica que la nueva fecha no sea la misma que la cita original
        LocalDateTime nuevaFechaHora = cita.getNuevaFechaHora();
        if(nuevaFechaHora.isEqual(citaActualizar.getFechaHora()))
            throw new CitasException("La nueva fecha es la misma que la original.", HttpStatus.BAD_REQUEST, "400");
        
        // Verifica que no exista otra cita con la misma fecha y hora
        Cita citaMismaFechaHora = citaRepository.findByFechaHora(nuevaFechaHora);
        if(citaMismaFechaHora != null)
            throw new CitasException("Ya existe una cita con la misma fecha y hora.", HttpStatus.BAD_REQUEST, "400");
        
        // Actualiza la fecha y hora de la cita
        citaActualizar.setFechaHora(nuevaFechaHora);
        
        // Actualiza la cita con la nueva fecha y hora
        citaActualizar = citaRepository.save(citaActualizar);
        
        // Regresa la respuesta
        return new ActualizarCitaResponse(citaActualizar.getEmpleado().getNombres(), LocalDateTime.now());
    }

    @Override
    public CancelarCitaResponse eliminar(@Valid CancelarCitaRequest cita) {
        
        Cita citaEliminar = citaRepository.findById(cita.getIdCita()).orElse(null);
        if(citaEliminar == null)
            throw new CitasException("La cita no existe.", HttpStatus.BAD_REQUEST, "400");
        
        citaRepository.delete(citaEliminar);
        return new CancelarCitaResponse(citaEliminar.getEmpleado().getNombres(), LocalDateTime.now());
    }

    @Override
    public List<CitaDTO> obtenerTodas() {
        List<Cita> citasEncontradas = citaRepository.findAll();
        List<CitaDTO> citasEncontradasDTO = CitaMapper.toDTOList(citasEncontradas);
        return citasEncontradasDTO;
    }

    @Override
    public CitaDTO obtenerPorId(@NotNull Integer id) {
        Optional<Cita> resultado = citaRepository.findById(id);
        Cita citaEncontrada = resultado.orElse(null);
        return (citaEncontrada != null) ? CitaMapper.toDTO(citaEncontrada) : null;
    }

    @Override
    public List<CitaDTO> obtenerPorFecha(LocalDate fecha) {
        return null;
    }

    @Override
    public List<CitaDTO> buscarPorNombreCurpPaciente(String nombre, String curp) {
        return null;
    }

    @Override
    public List<CitaDTO> obtenerPorFiltro(String empleado, LocalDate limite, Set<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin) {
        return null;
    }
}