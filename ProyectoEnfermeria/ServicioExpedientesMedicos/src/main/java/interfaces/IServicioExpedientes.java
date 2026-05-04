/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import response.DatosEmpleadoResponse;

/**
 *
 * @author Ramon Valencia
 */
public interface IServicioExpedientes {
    
    public List<DatosEmpleadoResponse> obtenerDatosPrincipalesEmpleados();
    
    
}
