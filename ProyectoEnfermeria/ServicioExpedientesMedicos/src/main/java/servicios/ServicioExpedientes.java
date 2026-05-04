/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import DAOs.DetalleExtraRepository;
import DAOs.ExpedienteMedicoRepository;
import DAOs.RegistroMedicoRepository;
import entidades.DetalleExtra;
import entidades.ExpedienteMedico;
import entidades.RegistroMedico;
import exceptions.ExpedientesException;
import interfaces.IServicioExpedientes;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import mapper.DetalleExtraMapper;
import mapper.ExpedienteMedicoMapper;
import mapper.RegistroMedicoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import response.DatosEmpleadoResponse;
import response.DetalleResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 */
@Service
public class ServicioExpedientes implements IServicioExpedientes{
    
    private final ExpedienteMedicoRepository expedientesRepository;
    private final RegistroMedicoRepository registroMedicoRepository;
    private final DetalleExtraRepository detalleExtraRepository;
    
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy : HH:mm:ss");

    public ServicioExpedientes(ExpedienteMedicoRepository expedientesRepository, RegistroMedicoRepository registroMedicoRepository, DetalleExtraRepository detalleExtraRepository){
        this.expedientesRepository = expedientesRepository;
        this.registroMedicoRepository = registroMedicoRepository;
        this.detalleExtraRepository = detalleExtraRepository;
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
    public SignosVitalesResponse obtenerSignosVitalesEmpleado(Integer idExpediente) {
        ExpedienteMedico expediente = expedientesRepository.findById(idExpediente).get();
        RegistroMedico registro = registroMedicoRepository.findByExpedienteMedico(expediente);
        if (registro == null) {
            throw new ExpedientesException("No se encontro el registro medico.", HttpStatus.BAD_REQUEST, "400");
        }
        return RegistroMedicoMapper.toSignosVitalesResponse(registro);
    }
    
    public Map<String, List<DetalleResponse>> obtenerDetallesEmpleado(Integer idExpediente) {
        List<DetalleExtra> detalles = detalleExtraRepository.findByExpediente(idExpediente);
        if (detalles.isEmpty()) {
            throw new ExpedientesException("No se encontraron detalles medicos.", HttpStatus.BAD_REQUEST, "400");
        } 
        return DetalleExtraMapper.toDetalleResponseMap(detalles);
    }
}
