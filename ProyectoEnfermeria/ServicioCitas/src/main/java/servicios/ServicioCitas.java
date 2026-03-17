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
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mapper.CitaMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import request.CrearCitaRequest;
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
        Enfermero enfermero = enfermeroRepository.findById(cita.getIdEnfermero()).orElse(null);
        if(empleadoAsociado == null)
            throw new CitasException("No existe un enfermero asociado a la cita.", HttpStatus.BAD_REQUEST, "400");
        
        if(cita.getEstado() != EstadoCita.PENDIENTE)
            throw new CitasException("La cita debe estar pendiente.", HttpStatus.BAD_REQUEST, "400");
        
        Cita nuevaCita = CitaMapper.toEntityNew(cita);
        nuevaCita = citaRepository.save(nuevaCita);
        
        return (nuevaCita.getIdCita() != null) ? new CrearCitaResponse(nuevaCita.getEmpleado().getNombres(), nuevaCita.getFechaHora()) : null;

    }

    @Override
    public CitaDTO actualizar(CitaDTO cita){
        Cita nuevaCita = CitaMapper.toEntityOld(cita);
        citaRepository.save(nuevaCita);
        return null;
    }

    @Override
    public CitaDTO eliminar(CitaDTO cita) {
        
        Cita nuevaCita = CitaMapper.toEntityOld(cita);
        citaRepository.save(nuevaCita);
        return null;
    }

    @Override
    public List<CitaDTO> obtenerTodas() {
        return null;
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