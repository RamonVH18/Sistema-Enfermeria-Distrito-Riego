package interfaces;

import dtos.ReporteRegistroDTO;
import java.util.List;
import java.util.Map;
import request.AgregarExpedienteRequest;
import response.AgregarExpedienteResponse;
import response.DatosEmpleadoResponse;
import response.AntecedenteResponse;
import response.AtributoFisicoResponse;
import response.ExpedienteConfigResponse;
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
    
    public Map<String, List<AntecedenteResponse>> obtenerAntecedentesEmpleado(Integer idExpediente);
    
    public Map<String, AtributoFisicoResponse> obtenerAtributosFisicosEmpleados(Integer idExpediente);
    
    public List<ReporteRegistroDTO> obtenerUltimoRegistroPacientes();
    
    public ExpedienteConfigResponse obtenerInfoConfiguracionExpediente();
}