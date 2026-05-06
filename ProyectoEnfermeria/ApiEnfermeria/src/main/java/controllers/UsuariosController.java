package controllers;

import entidades.Usuario;
import interfaces.IServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.UsuarioResponse;

/**
 *
 * @author isaac
 */
@RestController
@RequestMapping("/auth")
public class UsuariosController {

    @Autowired
    private final IServicioUsuarios servicioUsuarios;

    public UsuariosController(IServicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> authenticate(@RequestBody Usuario loginRequest) {
        return ResponseEntity.ok(servicioUsuarios.login(loginRequest.getEmail(), loginRequest.getPassword()));
    }
}