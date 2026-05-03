/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dtos.ExpedienteMedicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author isaac
 */
//@RestController
//@RequestMapping("/expedientes")
//public class ExpedientesController {
//    
//    @Autowired
//    private IServicioExpedientes servicioExpediente;
//
//    // Obtener expediente por ID de empleado
//    @GetMapping("/empleado/{id}")
//    public ResponseEntity<ExpedienteMedicoDTO> obtenerPorEmpleado(@PathVariable Long id) {
//        ExpedienteMedicoDTO expediente = servicioExpediente.buscarPorEmpleadoId(id);
//        if (expediente != null) {
//            return ResponseEntity.ok(expediente);
//        }
//        return ResponseEntity.notFound().build(); // Retorna 404 si no tiene historial
//    }
//
//    
//    @PostMapping
//    public ResponseEntity<ExpedienteMedicoDTO> crear(@RequestBody ExpedienteMedicoDTO dto) {
//        return new ResponseEntity<>(servicioExpediente.guardar(dto), HttpStatus.CREATED);
//    }
//
//    
//    @PutMapping("/{id}")
//    public ResponseEntity<ExpedienteMedicoDTO> actualizar(@PathVariable Long id, @RequestBody ExpedienteMedicoDTO dto) {
//        return ResponseEntity.ok(servicioExpediente.actualizar(id, dto));
//    }
//
//}
