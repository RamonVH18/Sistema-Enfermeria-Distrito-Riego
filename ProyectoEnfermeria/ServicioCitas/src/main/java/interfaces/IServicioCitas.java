package interfaces;

import dtos.CitaDTO;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import request.CrearCitaRequest;
import response.CrearCitaResponse;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public interface IServicioCitas {
    
    public CrearCitaResponse crear(CrearCitaRequest cita);
    
    public CitaDTO actualizar(CitaDTO cita);
    
    public CitaDTO eliminar(CitaDTO cita);
    
    // No creo que se llegue a necesitar
    public List<CitaDTO> obtenerTodas();
    
    // Creo que este método tiene más sentido en la clase DAO
    public CitaDTO obtenerPorId(Integer id);
    
    
    public List<CitaDTO> obtenerPorFecha(LocalDate fecha);
    
    public List<CitaDTO> buscarPorNombreCurpPaciente(String nombre, String curp);
    
    // PROVISIONAL
    public List<CitaDTO> obtenerPorFiltro(
            String empleado, 
            LocalDate limite, 
            Set<DayOfWeek> dias, 
            LocalTime horaInicio, 
            LocalTime horaFin
    );
}