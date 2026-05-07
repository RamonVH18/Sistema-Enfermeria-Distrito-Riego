package controllers;

import interfaces.IServicioExpedientes;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.DatosEmpleadoResponse;
import response.DetalleResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 */
@RestController
@RequestMapping("/expedientes")
public class ExpedientesController {
    
    @Autowired
    private IServicioExpedientes servicioExpediente;

    @GetMapping
    public ResponseEntity<List<DatosEmpleadoResponse>> obtenerPorEmpleado() {
        return ResponseEntity.ok(servicioExpediente.obtenerDatosPrincipalesEmpleados()); 
    }
    
    @GetMapping("/signos/{id}")
    public ResponseEntity<SignosVitalesResponse> obtenerSignosVitalesEmpleado(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioExpediente.obtenerSignosVitalesEmpleado(id));
    }
    
    @GetMapping("/antecedentes/{id}")
    public ResponseEntity<Map<String, List<DetalleResponse>>> obtenerAntecedentesEmpleado(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioExpediente.obtenerAntecedentesEmpleado(id));
    }

}