package mapper;

import dtos.ExpedienteMedicoDTO;
import entidades.Empleado;
import entidades.ExpedienteMedico;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ExpedienteMedicoMapper {
    
    public static ExpedienteMedico toEntity(ExpedienteMedicoDTO dto){
        ExpedienteMedico entity = new ExpedienteMedico();
        entity.setId(dto.getId());
        
        Empleado empleado = new Empleado();
        empleado.setId(dto.getIdEmpleado());
        entity.setEmpleado(empleado);
        
        entity.setTipoSangre(dto.getTipoSangre());
        entity.setNumeroSeguridadSocial(dto.getNumeroSeguridadSocial());
        
        entity.setAntecedentes(AntecedenteExpedienteMedicoMapper.toEntitySet(dto.getAntecedentes()));
        entity.setAtributosFisicos(AtributoFisicoExpedienteMedicoMapper.toEntitySet(dto.getAtributosFisicos()));
        entity.setDetallesExtra(DetalleExtraExpedienteMedicoMapper.toEntitySet(dto.getDetallesExtra()));
        return entity;
    }
    
    public static ExpedienteMedicoDTO toDTO(ExpedienteMedico entity){
        ExpedienteMedicoDTO dto = new ExpedienteMedicoDTO();
        dto.setId(entity.getId());
        
        Empleado empleado = entity.getEmpleado();
        dto.setIdEmpleado((empleado != null) ? empleado.getId() : null);
        
        dto.setTipoSangre(entity.getTipoSangre());
        dto.setNumeroSeguridadSocial(entity.getNumeroSeguridadSocial());
        
        dto.setAntecedentes(AntecedenteExpedienteMedicoMapper.toDTOSet(entity.getAntecedentes()));
        dto.setAtributosFisicos(AtributoFisicoExpedienteMedicoMapper.toDTOSet(entity.getAtributosFisicos()));
        dto.setDetallesExtra(DetalleExtraExpedienteMedicoMapper.toDTOSet(entity.getDetallesExtra()));
        
        return dto;
    }
    
    public static List<ExpedienteMedico> toEntityList(List<ExpedienteMedicoDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).toList();
    }
    
    public static List<ExpedienteMedicoDTO> toDTOList(List<ExpedienteMedico> entities){
        return entities.stream().map(entity -> toDTO(entity)).toList();
    }
}