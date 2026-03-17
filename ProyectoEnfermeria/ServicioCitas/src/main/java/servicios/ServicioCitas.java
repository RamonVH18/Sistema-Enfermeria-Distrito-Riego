package servicios;

import DAOs.CitaRepository;
import dtos.CitaDTO;
import entidades.Cita;
import interfaces.IServicioCitas;
import jakarta.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import mapper.CitaMapper;
import org.springframework.stereotype.Service;
import request.CrearCitaRequest;
import response.CrearCitaResponse;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
@Service
public class ServicioCitas implements IServicioCitas{
    
    private final CitaRepository citaRepository;

    public ServicioCitas(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }
    
    @Override
    public CrearCitaResponse crear(@Valid CrearCitaRequest cita) {
        
        Cita nuevaCita = CitaMapper.toEntityNew(cita);
        // Guarda la nueva cita
        return null;

    }

    @Override
    public CitaDTO actualizar(CitaDTO cita){
        return null;
    }

    @Override
    public CitaDTO eliminar(CitaDTO cita) {
        return null;
    }

    @Override
    public List<CitaDTO> obtenerTodas() {
        return null;
    }

    @Override
    public CitaDTO obtenerPorId(Integer id) {
        return null;
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