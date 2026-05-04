package response;

import dtos.ReporteEmpleadosDTO;

/**
 * Respuesta que envuelve el reporte de empleados.
 */
public class ReporteEmpleadosResponse {
    
    private ReporteEmpleadosDTO reporte;

    public ReporteEmpleadosResponse() {}

    public ReporteEmpleadosResponse(ReporteEmpleadosDTO reporte) {
        this.reporte = reporte;
    }

    public ReporteEmpleadosDTO getReporte() {
        return reporte;
    }

    public void setReporte(ReporteEmpleadosDTO reporte) {
        this.reporte = reporte;
    }
}
