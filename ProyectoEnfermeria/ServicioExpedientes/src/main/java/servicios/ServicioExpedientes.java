/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dtos.ExpedienteMedicoDTO;
import entidades.ExpedienteMedico;
import interfaces.IServicioExpedientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dtos.ExpedienteMedicoDTO;
import entidades.ExpedienteMedico;
import interfaces.IServicioExpedientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author isaac
 */
@Service
public class ServicioExpedientes implements IServicioExpedientes {

//    @Autowired
//    private ExpedienteMedicoRepository repositorio;
//
//    @Autowired
//    
//    private ExpedienteMapper mapper;

    @Override
    public ExpedienteMedicoDTO buscarPorEmpleadoId(Long idEmpleado) {
//        ExpedienteMedico entidad = repositorio.findByIdEmpleado(idEmpleado);
//        return (entidad != null) ? mapper.toDTO(entidad) : null;
        return null;
//        ExpedienteMedico entidad = repositorio.findByIdEmpleado(idEmpleado);
//        return (entidad != null) ? mapper.toDTO(entidad) : null;
    }


    @Override
    public ExpedienteMedicoDTO guardar(ExpedienteMedicoDTO dto) {
//        ExpedienteMedico entidad = mapper.toEntity(dto);
//        return mapper.toDTO(repositorio.save(entidad));
        return null;
//        ExpedienteMedico entidad = mapper.toEntity(dto);
//        return mapper.toDTO(repositorio.save(entidad));
    }

    @Override
    public ExpedienteMedicoDTO actualizar(Long id, ExpedienteMedicoDTO dto) {
        // Lógica de actualización similar a ServicioCitas
        return dto;
    }
}
