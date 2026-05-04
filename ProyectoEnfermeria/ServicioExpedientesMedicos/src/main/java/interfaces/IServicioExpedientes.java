/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.ExpedienteMedico;
import java.util.List;
import response.DatosEmpleadoResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 */
public interface IServicioExpedientes {
    
    public List<DatosEmpleadoResponse> obtenerDatosPrincipalesEmpleados();
    
    public SignosVitalesResponse obtenerSignosVitalesEmpleado(ExpedienteMedico expediente);
    
}
