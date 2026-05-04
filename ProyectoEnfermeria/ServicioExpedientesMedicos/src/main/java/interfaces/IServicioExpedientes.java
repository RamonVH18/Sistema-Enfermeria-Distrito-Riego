/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import java.util.Map;
import response.DatosEmpleadoResponse;
import response.DetalleResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 */
public interface IServicioExpedientes {
    
    public List<DatosEmpleadoResponse> obtenerDatosPrincipalesEmpleados();
    
    public SignosVitalesResponse obtenerSignosVitalesEmpleado(Integer idExpediente);
    
    public Map<String, List<DetalleResponse>> obtenerDetallesEmpleado(Integer idExpediente);
    
}
