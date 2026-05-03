/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.EmpleadoDTO;
import jakarta.validation.Valid;
import java.util.List;
import response.EmpleadoOptionResponse;

/**
 *
 * @author Ramon Valencia
 */
public interface IServicioEmpleado {
    
    public List<EmpleadoOptionResponse> obtenerEmpleados();
    
    public List<EmpleadoOptionResponse> obtenerEmpleadosPorNombre(@Valid String filtroNombre);
    
    public List<EmpleadoDTO> obtenerPacientesHistorial();
    
    public List<EmpleadoDTO> obtenerTodosPacientes();
    
    
}
