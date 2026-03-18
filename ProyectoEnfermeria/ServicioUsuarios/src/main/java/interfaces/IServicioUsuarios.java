/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import request.IniciarSesionRequest;
import response.IniciarSesionResponse;

/**
 *
 * @author isaac
 */
public interface IServicioUsuarios {

    public List<UsuarioDTO> obtenerUsuarios();

    public UsuarioDTO obtenerPorId(@NotNull Integer id);
    
    public UsuarioDTO buscarUsuarioPorEmail(String email);

     public IniciarSesionResponse crearIniciarSesionRequest(@Valid IniciarSesionRequest iniciarSesion);
     
     
   
}
