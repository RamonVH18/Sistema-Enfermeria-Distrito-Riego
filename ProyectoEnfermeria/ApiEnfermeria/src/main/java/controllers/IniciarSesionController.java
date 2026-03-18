/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dtos.CitaDTO;
import dtos.UsuarioDTO;
import interfaces.IServicioUsuarios;
import java.util.List;
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
import response.IniciarSesionResponse;

/**
 *
 * @author isaac
 */
@RestController
@RequestMapping("/auth")
public class IniciarSesionController {

    private IServicioUsuarios servicioUsuarios;

    public IniciarSesionController(IServicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    @PostMapping("/login")
    public ResponseEntity<IniciarSesionResponse> iniciarSesion(@RequestBody IniciarSesionRequest request) {
        IniciarSesionResponse response = servicioUsuarios.crearIniciarSesionRequest(request);
        System.out.println("Ya llego el Ramonson");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioUsuarios.obtenerPorId(id));
    }

}
