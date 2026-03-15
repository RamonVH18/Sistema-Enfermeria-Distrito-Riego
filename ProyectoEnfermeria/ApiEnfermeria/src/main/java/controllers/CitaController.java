/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dtos.CitaDTO;
import interfaces.IServicioCitas;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import request.CrearCitaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ramon Valencia
 */
@RestController
@RequestMapping("/citas")
public class CitaController {

    private IServicioCitas servicioCitas;

    public CitaController(IServicioCitas servicioCitas) {
        this.servicioCitas = servicioCitas;
    }

    @PostMapping
    public ResponseEntity<CitaDTO> crearCita(@RequestBody CrearCitaRequest request) {
        CitaDTO citaDTO = servicioCitas.guardarCita(request);

        return new ResponseEntity<>(citaDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> obtenerTodas() {
        return ResponseEntity.ok(servicioCitas.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioCitas.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CitaDTO>> obtenerPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(servicioCitas.obtenerPorFecha(fecha));
    }

    @GetMapping("/buscar/paciente")
    public ResponseEntity<List<CitaDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<CitaDTO> citas = servicioCitas.buscarPorNombrePaciente(nombre);
        return ResponseEntity.ok(citas);
    }
}
