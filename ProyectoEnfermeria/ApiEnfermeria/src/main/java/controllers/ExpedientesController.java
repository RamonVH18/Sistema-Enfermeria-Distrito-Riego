package controllers;

import interfaces.IServicioExpedientes;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import request.AgregarExpedienteRequest;
import response.AgregarExpedienteResponse;
import response.DatosEmpleadoResponse;
import response.AntecedenteResponse;
import response.AtributoFisicoResponse;
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
    
    @PostMapping
    public ResponseEntity<AgregarExpedienteResponse> agregarExpedienteEmpleado(@RequestBody AgregarExpedienteRequest expediente) {
        AgregarExpedienteResponse response = servicioExpediente.agregarExpedienteEmpleado(expediente);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<DatosEmpleadoResponse>> obtenerPorEmpleado() {
        return ResponseEntity.ok(servicioExpediente.obtenerDatosPrincipalesEmpleados()); 
    }
    
    @GetMapping("/signos/{id}")
    public ResponseEntity<SignosVitalesResponse> obtenerSignosVitalesEmpleado(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioExpediente.obtenerSignosVitalesEmpleado(id));
    }
    
    @GetMapping("/antecedentes/{id}")
    public ResponseEntity<Map<String, List<AntecedenteResponse>>> obtenerAntecedentesEmpleado(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioExpediente.obtenerAntecedentesEmpleado(id));
    }
    
    @GetMapping("/atributos/{id}")
    public ResponseEntity<Map<String, AtributoFisicoResponse>> obtenerAtributosFisicosEmpleados(@PathVariable Integer id) {
        return ResponseEntity.ok(servicioExpediente.obtenerAtributosFisicosEmpleados(id));
    }
    

}