/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import DAOs.EmpleadoRepository;
import DAOs.EnfermeroRepository;
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
    
    @Autowired
    private EnfermeroRepository enfermeroRepository;
    
    /**
     * Método para validar el inicio de sesión manual
     * @param email El correo ingresado
     * @param password La contraseña ingresada
     * @return El objeto Usuario si es válido, de lo contrario lanza una excepción
     */
    @Override
    public UsuarioResponse login(String email, String password) {
    // 1. Buscamos al usuario
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Validamos contraseña
        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 3. REQUISITO NUEVO: ¿Es enfermero?
        // Buscamos en la tabla de enfermeros usando el ID del empleado vinculado al usuario
        boolean esEnfermero = enfermeroRepository.existsByEmpleadoIdEmpleado(usuario.getEmpleado().getId());

        if (!esEnfermero) {
            // Ética profesional: Acceso denegado por falta de privilegios
            throw new RuntimeException("Acceso denegado: Solo el personal de enfermería puede iniciar sesión.");
        }

        // 4. Si todo es correcto, devolvemos el DTO
        String nombreCompleto = usuario.getEmpleado().getNombres() + " " + usuario.getEmpleado().getApellidoPaterno();
        return new UsuarioResponse(nombreCompleto, usuario.getEmail());
    }
}
