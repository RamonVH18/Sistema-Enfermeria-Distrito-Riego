package controllers;

import dtos.ReporteEmpleadosDTO;
import interfaces.IServicioReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.ReporteEmpleadosResponse;

/**
 * Controlador para la generación de reportes.
 * 
 * @author isaac
 */
@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private IServicioReportes servicioReportes;

    @GetMapping("/empleados")
    public ResponseEntity<ReporteEmpleadosResponse> obtenerReporteEmpleados() {
        ReporteEmpleadosDTO reporte = servicioReportes.generarReporteEmpleados();
        return ResponseEntity.ok(new ReporteEmpleadosResponse(reporte));
    }
}
