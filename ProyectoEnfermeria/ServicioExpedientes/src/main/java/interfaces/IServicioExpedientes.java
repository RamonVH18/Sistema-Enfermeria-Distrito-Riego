/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ExpedienteMedicoDTO;

/**
 *
 * @author isaac
 */
public interface IServicioExpedientes {
    
    ExpedienteMedicoDTO buscarPorEmpleadoId(Long idEmpleado);
    ExpedienteMedicoDTO guardar(ExpedienteMedicoDTO dto);
    ExpedienteMedicoDTO actualizar(Long id, ExpedienteMedicoDTO dto);
    
}
