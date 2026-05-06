package interfaces;

import dtos.ReporteRegistroDTO;
import java.util.List;
import java.util.Map;
import response.DatosEmpleadoResponse;
import response.DetalleResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva
 */
public interface IServicioExpedientes {
    
    public List<DatosEmpleadoResponse> obtenerDatosPrincipalesEmpleados();
    
    public SignosVitalesResponse obtenerSignosVitalesEmpleado(Integer idExpediente);
    
    public Map<String, List<DetalleResponse>> obtenerDetallesEmpleado(Integer idExpediente);
    
    public List<ReporteRegistroDTO> obtenerUltimoRegistroPacientes();
}