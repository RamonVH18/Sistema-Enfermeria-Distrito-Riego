package mapper;

import dtos.AtributoFisicoDTO;
import dtos.AtributoFisicoExpedienteMedicoDTO;
import dtos.ExpedienteMedicoDTO;
import entidades.AtributoFisico;
import entidades.AtributoFisicoExpedienteMedico;
import entidades.ExpedienteMedico;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class AtributoFisicoExpedienteMedicoMapper {
    
    public static AtributoFisicoExpedienteMedico toEntity(AtributoFisicoExpedienteMedicoDTO dto){
        AtributoFisicoExpedienteMedico entity = new AtributoFisicoExpedienteMedico();
        entity.setId(dto.getId());
        entity.setEspecificacion(dto.getEspecificacion());
        entity.setNota(dto.getNota());
        
        AtributoFisicoDTO atributoFisicoDTO = dto.getAtributoFisico();
        entity.setAtributoFisico((atributoFisicoDTO != null) ? AtributoFisicoMapper.toEntity(atributoFisicoDTO) : null);
        
        ExpedienteMedicoDTO expedienteMedicoDTO = dto.getExpedienteMedico();
        ExpedienteMedico expedienteMedico = new ExpedienteMedico();
        expedienteMedico.setId((expedienteMedicoDTO != null) ? expedienteMedicoDTO.getId() : null);
        entity.setExpedienteMedico(expedienteMedico);
        
        return entity;
    }
    
    public static AtributoFisicoExpedienteMedicoDTO toDTO(AtributoFisicoExpedienteMedico entity){
        AtributoFisicoExpedienteMedicoDTO dto = new AtributoFisicoExpedienteMedicoDTO();
        dto.setId(entity.getId());
        dto.setEspecificacion(entity.getEspecificacion());
        dto.setNota(entity.getNota());
        
        AtributoFisico atributoFisico = entity.getAtributoFisico();
        dto.setAtributoFisico((atributoFisico != null) ? AtributoFisicoMapper.toDTO(atributoFisico) : null);
        
        ExpedienteMedico expedienteMedico = entity.getExpedienteMedico();
        ExpedienteMedicoDTO expedienteMedicoDTO = new ExpedienteMedicoDTO();
        expedienteMedicoDTO.setId((expedienteMedico != null) ? expedienteMedico.getId() : null);
        dto.setExpedienteMedico(expedienteMedicoDTO);
        
        return dto;
    }
    
    public static Set<AtributoFisicoExpedienteMedico> toEntitySet(Set<AtributoFisicoExpedienteMedicoDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toSet());
    }
    
    public static Set<AtributoFisicoExpedienteMedicoDTO> toDTOSet(Set<AtributoFisicoExpedienteMedico> entities){
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toSet());
    }
}