/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import DAOs.ExpedienteMedicoRepository;
import DAOs.RegistroMedicoRepository;
import entidades.ExpedienteMedico;
import entidades.RegistroMedico;
import exceptions.ExpedientesException;
import interfaces.IServicioExpedientes;
import java.time.format.DateTimeFormatter;
import java.util.List;
import mapper.ExpedienteMedicoMapper;
import mapper.RegistroMedicoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import response.DatosEmpleadoResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 */
@Service
public class ServicioExpedientes implements IServicioExpedientes{
    
    private final ExpedienteMedicoRepository expedientesRepository;
    private final RegistroMedicoRepository registroMedicoRepository;
    
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy : HH:mm:ss");

    public ServicioExpedientes(ExpedienteMedicoRepository expedientesRepository, RegistroMedicoRepository registroMedicoRepository){
        this.expedientesRepository = expedientesRepository;
        this.registroMedicoRepository = registroMedicoRepository;
    }

    @Override
    public List<DatosEmpleadoResponse> obtenerDatosPrincipalesEmpleados() {
        List<ExpedienteMedico> expedientes = expedientesRepository.findAll();
        if (expedientes.isEmpty()) {
            throw new ExpedientesException("No existen registros.", HttpStatus.BAD_REQUEST,"400");
        }
        return ExpedienteMedicoMapper.toDatosEmpleadoResponse(expedientes);
    }
    
    @Override
    public SignosVitalesResponse obtenerSignosVitalesEmpleado(ExpedienteMedico expediente) {
        RegistroMedico registro = registroMedicoRepository.findByExpedienteMedico(expediente);
        if (registro == null) {
            throw new ExpedientesException("No se encontro el registro medico.", HttpStatus.BAD_REQUEST, "400");
        }
        return RegistroMedicoMapper.toSignosVitalesResponse(registro);
    }
    
    
}
