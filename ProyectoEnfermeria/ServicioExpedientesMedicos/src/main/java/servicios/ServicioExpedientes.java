/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import DAOs.ExpedienteMedicoRepository;
import entidades.ExpedienteMedico;
import exceptions.ExpedientesException;
import interfaces.IServicioExpedientes;
import java.time.format.DateTimeFormatter;
import java.util.List;
import mapper.ExpedienteMedicoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import response.DatosEmpleadoResponse;

/**
 *
 * @author Ramon Valencia
 */
@Service
public class ServicioExpedientes implements IServicioExpedientes{
    
    private final ExpedienteMedicoRepository expedientesRepository;
    
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy : HH:mm:ss");

    public ServicioExpedientes(ExpedienteMedicoRepository expedientesRepository){
        this.expedientesRepository = expedientesRepository;
    }

    @Override
    public List<DatosEmpleadoResponse> obtenerDatosPrincipalesEmpleados() {
        List<ExpedienteMedico> expedientes = expedientesRepository.findAll();
        if (expedientes.isEmpty()) {
            throw new ExpedientesException("No existen registros.", HttpStatus.BAD_REQUEST,"400");
        }
        return ExpedienteMedicoMapper.toDatosEmpleadoResponse(expedientes);
    }
    
    
}
