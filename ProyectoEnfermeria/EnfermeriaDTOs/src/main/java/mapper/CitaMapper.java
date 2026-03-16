package mapper;

import dtos.CitaDTO;
import request.CrearCitaRequest;
import entidades.Cita;
import entidades.Empleado;
import entidades.Enfermero;
import entidades.Serie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class CitaMapper {
    
    public static Cita toEntityNew(CrearCitaRequest dto) {
        Cita entity = new Cita();
        entity.setFechaHora(dto.getFechaHora());
        entity.setDuracionMin(dto.getDuracionMin());
        entity.setEstado(dto.getEstado());
        entity.setMotivo(dto.getMotivo());
        
        Empleado empleado = new Empleado();
        empleado.setId(dto.getIdEmpleado());
        entity.setEmpleado(empleado);
        
        Enfermero enfermero = new Enfermero();
        enfermero.setId(dto.getIdEnfermero());
        entity.setEnfermero(enfermero);
        
        return entity;
    }
    
    public static Cita toEntityOld(CitaDTO dto){
        Cita entity = new Cita();
        entity.setIdCita(dto.getIdCita());
        entity.setFechaHora(dto.getFechaHora());
        entity.setDuracionMin(dto.getDuracionMin());
        entity.setEstado(dto.getEstado());
        entity.setMotivo(dto.getMotivo());
        
        Serie serie = new Serie();
        serie.setId(dto.getIdSerie());
        entity.setSerie(serie);
        
        Empleado empleado = new Empleado();
        empleado.setId(dto.getIdEmpleado());
        entity.setEmpleado(empleado);
        
        Enfermero enfermero = new Enfermero();
        enfermero.setId(dto.getIdEnfermero());
        entity.setEnfermero(enfermero);
        
        return entity;
    }
    
    public static CitaDTO toDTO(Cita entity) {
        CitaDTO dto = new CitaDTO();
        dto.setFechaHora(entity.getFechaHora());
        dto.setDuracionMin(entity.getDuracionMin());
        dto.setEstado(entity.getEstado());
        dto.setMotivo(entity.getMotivo());
        
        Serie serie = entity.getSerie();
        if(serie != null)
            dto.setIdSerie(serie.getId());
        
        Empleado empleado = entity.getEmpleado();
        if(empleado != null)
            dto.setIdEmpleado(empleado.getId());
        
        Enfermero enfermero = entity.getEnfermero();
        if(enfermero != null)
            dto.setIdEnfermero(enfermero.getId());
        
        return dto;
    }
    
    public static List<CitaDTO> toDTOList(List<Cita> entities){
        List<CitaDTO> dtos = new ArrayList<>();
        for(Cita entity: entities){
            CitaDTO dto = toDTO(entity);
            dtos.add(dto);
        }
        return dtos;
    }
}