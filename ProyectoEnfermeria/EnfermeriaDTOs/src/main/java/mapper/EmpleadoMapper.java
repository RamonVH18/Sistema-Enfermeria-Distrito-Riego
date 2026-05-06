package mapper;

import dtos.EmpleadoDTO;
import entidades.Empleado;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import response.EmpleadoOptionResponse;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class EmpleadoMapper {

    public static EmpleadoOptionResponse toOptionResponse(Empleado e) {
        return new EmpleadoOptionResponse(
                e.getId(),
                e.getNombres()
                + " " + e.getApellidoPaterno()
                + " " + e.getApellidoMaterno()
        );
    }

    public static List<EmpleadoOptionResponse> toOptionResponse(List<Empleado> empleados) {
        List<EmpleadoOptionResponse> emps = new ArrayList<>();
        for (Empleado e : empleados) {
            emps.add(
                    new EmpleadoOptionResponse(
                            e.getId(),
                            e.getNombres()
                            + " " + e.getApellidoPaterno()
                            + " " + e.getApellidoMaterno()
                    )
            );
        }

        return emps;
    }

    public static EmpleadoDTO toDTO(Empleado entidad) {
        if (entidad == null) {
            return null;
        }

        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setIdEmpleado(entidad.getId());
        dto.setNombres(entidad.getNombres());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        dto.setGenero(entidad.getGenero().toString());

        // Mapeo del Departamento usando el mapper anterior
        dto.setDepartamento(DepartamentoMapper.toDTO(entidad.getDepartamento()));

        // Calculamos la edad y la guardamos en el campo 'estado' como String
        if (entidad.getFechaNacimiento() != null) {
            int edad = Period.between(entidad.getFechaNacimiento(), LocalDate.now()).getYears();
            dto.setEstado(String.valueOf(edad));
        }

        return dto;
    }

    public static List<EmpleadoDTO> toDTOList(List<Empleado> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(EmpleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

}