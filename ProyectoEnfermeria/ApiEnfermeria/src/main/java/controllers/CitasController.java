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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import request.ActualizarCitaRequest;
import response.ActualizarCitaResponse;
import response.CitaPendienteResponse;
import response.CrearCitaResponse;

/**
 *
 * @author Ramon Valencia
 */
@RestController
@RequestMapping("/citas")
public class CitasController {

    private final IServicioCitas servicioCitas;

    public CitasController(IServicioCitas servicioCitas) {
        this.servicioCitas = servicioCitas;
    }

    @PostMapping
    public ResponseEntity<CrearCitaResponse> crearCita(@RequestBody CrearCitaRequest request) {
        CrearCitaResponse response = servicioCitas.crear(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping(params = "estado=PENDIENTE")
    public ResponseEntity<List<CitaPendienteResponse>> obtenerCitasPendientes(
            @RequestParam(name = "filtroFecha", required = false) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return ResponseEntity.ok(servicioCitas.obtenerPorFechaPendiente(fecha));
        }
        return ResponseEntity.ok(servicioCitas.obtenerCitasPendientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioCitas.obtenerPorId(id));
    }
    
    @PostMapping("/actualizar")
    public ResponseEntity<ActualizarCitaResponse> reagendarCita(@RequestBody ActualizarCitaRequest request){
        ActualizarCitaResponse response = servicioCitas.actualizar(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarCita(@PathVariable Integer id) {
        servicioCitas.cancelar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/completar")
    public ResponseEntity<Void> completarCita(@PathVariable Integer id) {
        servicioCitas.completar(id);
        return ResponseEntity.noContent().build();
    }
}