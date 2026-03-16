package servicios;

import dtos.CitaDTO;
import interfaces.IServicioCitas;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import request.CrearCitaRequest;
import response.CrearCitaResponse;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
@Service
public class ServicioCitas implements IServicioCitas{

    @Override
    public CrearCitaResponse crear(CrearCitaRequest cita) {
        //Ejemplo de como hacer las excepciones, cualquier duda ahi ando.
//        throw new CitasException("Isaac estuvo aqui", HttpStatus.CREATED, "67");
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public CitaDTO actualizar(CitaDTO cita){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CitaDTO eliminar(CitaDTO cita) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CitaDTO> obtenerTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CitaDTO obtenerPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CitaDTO> obtenerPorFecha(LocalDate fecha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CitaDTO> buscarPorNombreCurpPaciente(String nombre, String curp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CitaDTO> obtenerPorFiltro(String empleado, LocalDate limite, Set<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}