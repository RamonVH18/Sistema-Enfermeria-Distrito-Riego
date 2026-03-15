/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dtos.CitaDTO;
import request.CrearCitaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ramon Valencia
 */
@RestController
@RequestMapping("/citas")
public class CitaController {
    
//    @PostMapping
//    public ResponseEntity<CitaDTO> crearCita(@RequestBody CrearCitaRequest request) {
//        CitaDTO citaDTO = 
//    }
}
