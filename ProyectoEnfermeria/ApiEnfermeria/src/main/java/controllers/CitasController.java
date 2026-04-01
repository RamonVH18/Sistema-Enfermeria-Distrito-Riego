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
import response.CitaPendienteResponse;
import response.CrearCitaResponse;

/**
 *
 * @author Ramon Valencia
 */
@RestController
@RequestMapping("/citas")
public class CitasController {

    private IServicioCitas servicioCitas;

    public CitasController(IServicioCitas servicioCitas) {
        this.servicioCitas = servicioCitas;
    }

    @PostMapping
    public ResponseEntity<CrearCitaResponse> crearCita(@RequestBody CrearCitaRequest request) {
        CrearCitaResponse response = servicioCitas.crear(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping(params = "estado=PENDIENTE")
    public ResponseEntity<List<CitaPendienteResponse>> obtenerCitasPendientes() {
        return ResponseEntity.ok(servicioCitas.obtenerCitasPendientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioCitas.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CitaDTO>> obtenerPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(servicioCitas.obtenerPorFechaPendiente(fecha));
    }

    @GetMapping("/buscar/paciente")
    public ResponseEntity<List<CitaDTO>> buscarPorNombreCurp(@RequestParam String nombreCurp) {
        List<CitaDTO> citas = servicioCitas.buscarPorNombreCurpPacientePendiente(nombreCurp);
        return ResponseEntity.ok(citas);
    }
}