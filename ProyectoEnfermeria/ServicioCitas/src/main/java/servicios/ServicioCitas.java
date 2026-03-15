package servicios;

import dtos.CitaDTO;
import interfaces.IServicioCitas;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import request.CrearCitaRequest;

/**
 *
 * @author Leonardo Flores Leyva
 */
@Service
public class ServicioCitas implements IServicioCitas{

    @Override
    public CitaDTO guardarCita(CrearCitaRequest request) {
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
    public List<CitaDTO> buscarPorNombrePaciente(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}