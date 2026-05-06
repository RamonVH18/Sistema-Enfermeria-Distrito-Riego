package servicios;

import repositorios.DetalleExtraRepository;
import repositorios.ExpedienteMedicoRepository;
import repositorios.RegistroMedicoRepository;
import dtos.ReporteRegistroDTO;
import entidades.DetalleExtra;
import entidades.ExpedienteMedico;
import entidades.RegistroMedico;
import excepciones.ExpedientesException;
import interfaces.IServicioExpedientes;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mapper.DetalleExtraMapper;
import mapper.ExpedienteMedicoMapper;
import mapper.RegistroMedicoMapper;
import mapper.ReporteRegistroMapper;
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

    public ServicioExpedientes(
            ExpedienteMedicoRepository expedientesRepository, 
            RegistroMedicoRepository registroMedicoRepository, 
            DetalleExtraRepository detalleExtraRepository
    ){
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
        RegistroMedico registro = registroMedicoRepository.findFirstByExpedienteMedicoOrderByFechaCreacion(expediente);
        if (registro == null) {
            throw new ExpedientesException("No se encontro el registro medico.", HttpStatus.BAD_REQUEST, "400");
        }
        return RegistroMedicoMapper.toSignosVitalesResponse(registro);
    }
    
    @Override
    public Map<String, List<DetalleResponse>> obtenerDetallesEmpleado(Integer idExpediente) {
        List<DetalleExtra> detalles = detalleExtraRepository.findByExpediente(idExpediente);
        if (detalles.isEmpty()) {
            throw new ExpedientesException("No se encontraron detalles medicos.", HttpStatus.BAD_REQUEST, "400");
        } 
        return DetalleExtraMapper.toDetalleResponseMap(detalles);
    }
    
    @Override
    public List<ReporteRegistroDTO> obtenerUltimoRegistroPacientes(){
        
        List<ReporteRegistroDTO> registrosMedicosDTO = new ArrayList<>();
        
        List<RegistroMedico> registrosMedicos = registroMedicoRepository.findAllLastRecordEach();
        // Cada registro se mapea a su dto y se agrega a la lista
        registrosMedicos.stream().forEach(r -> {registrosMedicosDTO.add(ReporteRegistroMapper.toDTO(r));});

        return registrosMedicosDTO;
    }
}