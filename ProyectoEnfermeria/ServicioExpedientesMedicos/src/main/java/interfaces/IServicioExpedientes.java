package interfaces;

import dtos.ReporteRegistroDTO;
import java.util.List;
import java.util.Map;
import request.AgregarExpedienteRequest;
import response.AgregarExpedienteResponse;
import response.DatosEmpleadoResponse;
import response.DetalleResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva
 */
public interface IServicioExpedientes {
    
    public AgregarExpedienteResponse agregarExpedienteEmpleado(AgregarExpedienteRequest expediente);
    
    public List<DatosEmpleadoResponse> obtenerDatosPrincipalesEmpleados();
    
    public SignosVitalesResponse obtenerSignosVitalesEmpleado(Integer idExpediente);
    
    public Map<String, List<DetalleResponse>> obtenerAntecedentesEmpleado(Integer idExpediente);
    
    
    public List<ReporteRegistroDTO> obtenerUltimoRegistroPacientes();
}