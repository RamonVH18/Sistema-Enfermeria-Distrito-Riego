package servicios;

import repositorios.DetalleExtraRepository;
import repositorios.ExpedienteMedicoRepository;
import repositorios.RegistroMedicoRepository;
import dtos.ReporteRegistroDTO;
import entidades.Detalle;
import entidades.DetalleExtra;
import entidades.Empleado;
import entidades.ExpedienteMedico;
import entidades.RegistroMedico;
import enums.AtributoFisico;
import enums.CategoriaDetalle;
import enums.TipoSangre;
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
import repositorios.DetalleRepository;
import repositorios.EmpleadoRepository;
import request.AgregarExpedienteRequest;
import request.AntecedentesRequest;
import request.AtributosFisicosRequest;
import response.AgregarExpedienteResponse;
import response.DatosEmpleadoResponse;
import response.AntecedenteResponse;
import response.AtributoFisicoResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 */
@Service
public class ServicioExpedientes implements IServicioExpedientes {

    private final EmpleadoRepository empleadoRepository;
    private final ExpedienteMedicoRepository expedientesRepository;
    private final RegistroMedicoRepository registroMedicoRepository;
    private final DetalleExtraRepository detalleExtraRepository;
    private final DetalleRepository detalleRepository;

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy : HH:mm:ss");

    public ServicioExpedientes(
            EmpleadoRepository empleadoRepository,
            ExpedienteMedicoRepository expedientesRepository,
            RegistroMedicoRepository registroMedicoRepository,
            DetalleExtraRepository detalleExtraRepository,
            DetalleRepository detalleRepository
    ) {
        this.empleadoRepository = empleadoRepository;
        this.expedientesRepository = expedientesRepository;
        this.registroMedicoRepository = registroMedicoRepository;
        this.detalleExtraRepository = detalleExtraRepository;
        this.detalleRepository = detalleRepository;
    }

    @Override
    public AgregarExpedienteResponse agregarExpedienteEmpleado(AgregarExpedienteRequest exp) {
        List<AntecedentesRequest> antecedentes = exp.getAntecedentes();
        Map<String, AtributosFisicosRequest> atributos = exp.getAtributos();
        Empleado empleado = empleadoRepository.getReferenceById(exp.getIdEmpleado());
        ExpedienteMedico expediente = new ExpedienteMedico(
                TipoSangre.desdeString(exp.getTipoSangre()),
                exp.getNss(),
                empleado
        );
        if (antecedentes != null) {
            for (AntecedentesRequest a : antecedentes) {
                Detalle detalle = detalleRepository.getReferenceById(a.getIdDetalle());

                expediente.getDetallesExtra().add(
                        new DetalleExtra(a.getDetalle(), detalle, expediente));
            }
        }
        if (antecedentes != null) {
            for (AtributoFisico a : AtributoFisico.values()) {
                AtributosFisicosRequest atributo = atributos.get(a.name());
                if (atributo == null) {
                    continue;
                }
                Detalle detalle = detalleRepository.getReferenceById(atributo.getIdDetalle());
                String valor = AtributosFisicosMapper.convertirPropiedadesAXML(atributo.getPropiedades(), a);
                expediente.getDetallesExtra().add(
                        new DetalleExtra(valor, detalle, expediente)
                );
            }
        }
        expediente = expedientesRepository.save(expediente);
        return new AgregarExpedienteResponse(expediente.getId());
    }

    @Override
    public List<DatosEmpleadoResponse> obtenerDatosPrincipalesEmpleados() {
        List<ExpedienteMedico> expedientes = expedientesRepository.findAll();
        if (expedientes.isEmpty()) {
            throw new ExpedientesException("No existen registros.", HttpStatus.BAD_REQUEST, "400");
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
    public Map<String, List<AntecedenteResponse>> obtenerAntecedentesEmpleado(Integer idExpediente) {
        List<DetalleExtra> detalles = detalleExtraRepository.findByExpedienteYCategoria(idExpediente, CategoriaDetalle.ANTECEDENTE);
        if (detalles.isEmpty()) {
            throw new ExpedientesException("No se encontraron detalles medicos.", HttpStatus.BAD_REQUEST, "400");
        }
        return DetalleExtraMapper.toDetalleResponseMap(detalles);
    }

    @Override
    public Map<String, AtributoFisicoResponse> obtenerAtributosFisicosEmpleados(Integer idExpediente) {
        List<DetalleExtra> detalles = detalleExtraRepository.findByExpedienteYCategoria(idExpediente, CategoriaDetalle.ATRIBUTO_FISICO);
        if (detalles.isEmpty()) {
            throw new ExpedientesException("No se encontraron detalles medicos.", HttpStatus.BAD_REQUEST, "400");
        }
        return AtributosFisicosMapper.toDetalleExtraResponse(detalles);
    }

    @Override
    public List<ReporteRegistroDTO> obtenerUltimoRegistroPacientes() {

        List<ReporteRegistroDTO> registrosMedicosDTO = new ArrayList<>();

        List<RegistroMedico> registrosMedicos = registroMedicoRepository.findAllLastRecordEach();
        // Cada registro se mapea a su dto y se agrega a la lista
        registrosMedicos.stream().forEach(r -> {
            registrosMedicosDTO.add(ReporteRegistroMapper.toDTO(r));
        });

        return registrosMedicosDTO;
    }
}
