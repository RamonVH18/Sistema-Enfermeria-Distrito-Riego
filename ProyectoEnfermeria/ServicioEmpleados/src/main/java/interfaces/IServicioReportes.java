package interfaces;

import dtos.ReporteEmpleadosDTO;

/**
 * Interfaz para el servicio de generación de reportes de empleados.
 * 
 * @author isaac
 */
public interface IServicioReportes {
    
    /**
     * Genera un reporte estadístico de los empleados y su actividad.
     * 
     * @return DTO con los datos del reporte.
     */
    ReporteEmpleadosDTO generarReporteEmpleados();
}
