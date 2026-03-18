/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import DAOs.EmpleadoRepository;
import entidades.Empleado;
import interfaces.IServicioEmpleado;
import java.util.List;
import mapper.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.EmpleadoOptionResponse;

/**
 *
 * @author Ramon Valencia
 */
@Service
public class ServicioEmpleado implements IServicioEmpleado {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Override
    public List<EmpleadoOptionResponse> obtenerEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        List<EmpleadoOptionResponse> emps = EmpleadoMapper.toOptionResponse(empleados) ;
        return emps;
    }

    @Override
    public List<EmpleadoOptionResponse> obtenerEmpleadosPorNombre(String filtroNombre) {
        List<Empleado> empleados = empleadoRepository.findByNameFilter(filtroNombre);
        List<EmpleadoOptionResponse> emps = EmpleadoMapper.toOptionResponse(empleados);
        return emps;
    }
    
}
