package mapper;

import dtos.AtributoFisicoDTO;
import entidades.AtributoFisico;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class AtributoFisicoMapper {
    
    public static AtributoFisico toEntity(AtributoFisicoDTO dto){
        AtributoFisico entity = new AtributoFisico();
        entity.setId(dto.getId());
        entity.setAtributo(dto.getAtributo());
        entity.setTipo(dto.getTipo());
        return entity;
    }
    
    public static AtributoFisicoDTO toDTO(AtributoFisico entity){
        AtributoFisicoDTO dto = new AtributoFisicoDTO();
        dto.setId(entity.getId());
        dto.setAtributo(entity.getAtributo());
        dto.setTipo(entity.getTipo());
        return dto;
    }
    
    public static List<AtributoFisico> toEntityList(List<AtributoFisicoDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).toList();
    }
    
    public static List<AtributoFisicoDTO> toDTOList(List<AtributoFisico> entities){
        return entities.stream().map(entity -> toDTO(entity)).toList();
    }
}