/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import interfaces.IServicioEmpleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import response.EmpleadoOptionResponse;

/**
 *
 * @author Ramon Valencia
 */
@RestController
@RequestMapping("/empleados")
public class EmpleadosController {
    @Autowired
    private IServicioEmpleado servicioEmpleado;
    
    @GetMapping
    public ResponseEntity<List<EmpleadoOptionResponse>> obtenerTodas() {
        return ResponseEntity.ok(servicioEmpleado.obtenerEmpleados());
    }
    
    @GetMapping("/options")
    public ResponseEntity<List<EmpleadoOptionResponse>> obtenerTodas(@RequestParam String filtroNombre) {
        return ResponseEntity.ok(servicioEmpleado.obtenerEmpleadosPorNombre(filtroNombre));
    }
    
}
