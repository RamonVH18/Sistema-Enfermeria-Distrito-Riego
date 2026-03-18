/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import DAOs.EmpleadoRepository;
import dtos.UsuarioDTO;
import interfaces.IServicioUsuarios;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import request.IniciarSesionRequest;
import response.IniciarSesionResponse;

/**
 *
 * @author isaac
 */
@Service
@Validated
public class ServicioUsuarios implements IServicioUsuarios {

    private final EmpleadoRepository empleadoRepository;

    public ServicioUsuarios(EmpleadoRepository empleadoRepo) {
        this.empleadoRepository = empleadoRepo;
    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO obtenerPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IniciarSesionResponse crearIniciarSesionRequest(IniciarSesionRequest iniciarSesion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
