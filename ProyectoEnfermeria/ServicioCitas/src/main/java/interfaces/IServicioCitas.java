package interfaces;

import dtos.CitaDTO;
import java.time.LocalDate;
import java.util.List;
import request.CrearCitaRequest;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public interface IServicioCitas {
    
    public CitaDTO guardarCita(CrearCitaRequest request);
    
    public List<CitaDTO> obtenerTodas();
    
    public CitaDTO obtenerPorId(Integer id);
    
    public List<CitaDTO> obtenerPorFecha(LocalDate fecha);
    
    List<CitaDTO> buscarPorNombrePaciente(String nombre);
}