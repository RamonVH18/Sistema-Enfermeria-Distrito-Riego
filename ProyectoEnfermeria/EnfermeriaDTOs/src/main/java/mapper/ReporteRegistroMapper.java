package mapper;

import dtos.ReporteRegistroDTO;
import entidades.Departamento;
import entidades.Empleado;
import entidades.ExpedienteMedico;
import entidades.RegistroMedico;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ReporteRegistroMapper {
    
    public ReporteRegistroDTO toDTO(RegistroMedico entity, Integer edad){
        // DTO a entregar
        ReporteRegistroDTO dto = new ReporteRegistroDTO();
        // Expediente médico del empleado
        ExpedienteMedico expediente = entity.getExpedienteMedico();
        // El empleado
        Empleado empleado = (expediente == null || expediente.getEmpleado() == null) ? new Empleado() : expediente.getEmpleado();
        
        // Información personal del empleado
        dto.setNombre(empleado.getNombreCompleto());
        
        Departamento departamento = empleado.getDepartamento();
        dto.setDepartamento((departamento == null) ? null : departamento.getNombre());
        
        dto.setSexo(empleado.getGenero());
        dto.setEdad(edad);
        
        Integer nss = (expediente == null) ? null : expediente.getNumeroSeguridadSocial();
        dto.setNss(nss);
        
        // Información del registro médico del empleado
        dto.setFechaRegistro(entity.getFechaCreacion());
        dto.setAltura(entity.getAltura());
        dto.setPeso(entity.getPeso());
        dto.setPresionSistolica(entity.getPresionSistolica());
        dto.setPresionDiatolica(entity.getPresionDiatolica());
        dto.setGlucosa(entity.getGlucosa());
        dto.setOxigenacion(entity.getOxigenacion());
        dto.setFrecuenciaCardiaca(entity.getFrecuenciaCardiaca());
        dto.setTemperatura(entity.getTemperatura());
        
        return dto;
    }
}