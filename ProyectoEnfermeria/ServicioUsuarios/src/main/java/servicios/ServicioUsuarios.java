/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import DAOs.EmpleadoRepository;
import DAOs.UsuarioRepository;
import dtos.UsuarioDTO;
import entidades.Usuario;
import interfaces.IServicioUsuarios;
import java.util.List;
import mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import request.IniciarSesionRequest;
import response.UsuarioResponse;

/**
 *
 * @author isaac
 */
@Service
@Validated
public class ServicioUsuarios implements IServicioUsuarios {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Método para validar el inicio de sesión manual
     * @param email El correo ingresado
     * @param password La contraseña ingresada
     * @return El objeto Usuario si es válido, de lo contrario lanza una excepción
     */
    @Override
    public UsuarioResponse login(String email, String password) {
        // 1. Buscamos al usuario por email
        Usuario u = usuarioRepository.findByEmail(email)
                .map(user -> {
                    // 2. Comparamos la contraseña
                    if (user.getPassword().equals(password)) {
                        return user; // Credenciales correctas
                    } else {
                        throw new RuntimeException("Contraseña incorrecta");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return UsuarioMapper.toOptionResponse(u);
    }
}
