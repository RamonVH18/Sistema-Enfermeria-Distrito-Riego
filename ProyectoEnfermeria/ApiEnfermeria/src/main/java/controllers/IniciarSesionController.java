/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dtos.CitaDTO;
import dtos.UsuarioDTO;
import entidades.Usuario;
import interfaces.IServicioUsuarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import request.IniciarSesionRequest;
import response.UsuarioResponse;

/**
 *
 * @author isaac
 */
@RestController
@RequestMapping("/auth")
public class IniciarSesionController {

    @Autowired
    private IServicioUsuarios servicioUsuarios;

    public IniciarSesionController(IServicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> authenticate(@RequestBody Usuario loginRequest) {
        return ResponseEntity.ok(servicioUsuarios.login(loginRequest.getEmail(), loginRequest.getPassword()));
    }
}
