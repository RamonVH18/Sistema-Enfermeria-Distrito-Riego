/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import repositorios.EmpleadoRepository;
import dtos.EmpleadoDTO;
import entidades.Empleado;
import interfaces.IServicioEmpleado;
import java.util.List;
import mapper.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import response.EmpleadoOptionResponse;

/**
 *
 * @author Ramon Valencia
 */
@Service
@Validated
public class ServicioEmpleado implements IServicioEmpleado {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<EmpleadoOptionResponse> obtenerEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        List<EmpleadoOptionResponse> emps = EmpleadoMapper.toOptionResponse(empleados);
        return emps;
    }

    @Override
    public List<EmpleadoOptionResponse> obtenerEmpleadosPorNombre(String filtroNombre) {
        List<Empleado> empleados = empleadoRepository.findByNameFilter(filtroNombre);
        List<EmpleadoOptionResponse> emps = EmpleadoMapper.toOptionResponse(empleados);
        return emps;
    }

    @Override
    public List<EmpleadoDTO> obtenerPacientesHistorial() {
        List<Empleado> entidades = empleadoRepository.findAll();
        return EmpleadoMapper.toDTOList(entidades);
    }

    @Override
    public List<EmpleadoDTO> obtenerTodosPacientes() {
        List<Empleado> entidades = empleadoRepository.findAll();
        return entidades.stream().map(entidad -> {
            EmpleadoDTO dto = EmpleadoMapper.toDTO(entidad);
            dto.setNombres(entidad.getNombres() + " " + entidad.getApellidoPaterno());
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }
}