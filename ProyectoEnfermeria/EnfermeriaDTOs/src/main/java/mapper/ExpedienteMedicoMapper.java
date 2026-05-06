package mapper;

import entidades.ExpedienteMedico;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import response.DatosEmpleadoResponse;

/**
 *
 * @author Ramon Valencia
 */
public class ExpedienteMedicoMapper {

    public static List<DatosEmpleadoResponse> toDatosEmpleadoResponse(List<ExpedienteMedico> expedientes) {
        return expedientes.stream()
                .map(e -> {
                    int edad = Period.between(e.getEmpleado().getFechaNacimiento(), LocalDate.now()).getYears();
                    return new DatosEmpleadoResponse(
                            e.getEmpleado().getNombreCompleto(),
                            e.getEmpleado().getId(),
                            e.getEmpleado().getDepartamento().getNombre(),
                            edad,
                            e.getTipoSangre().getValor(),
                            e.getEmpleado().getTelefono()
                    );
                })
                .collect(Collectors.toList());
    }
}