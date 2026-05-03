package mapper;

import dtos.DetalleExtraDTO;
import entidades.DetalleExtra;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class DetalleExtraMapper {
    
    public static DetalleExtra toEntity(DetalleExtraDTO dto){
        DetalleExtra entity = new DetalleExtra();
        entity.setId(dto.getId());
        entity.setDetalle(dto.getDetalle());
        return entity;
    }
    
    public static DetalleExtraDTO toDTO(DetalleExtra entity){
        DetalleExtraDTO dto = new DetalleExtraDTO();
        dto.setId(entity.getId());
        dto.setDetalle(dto.getDetalle());
        return dto;
    }
    
    public static List<DetalleExtra> toEntityList(List<DetalleExtraDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).toList();
    }
    
    public static List<DetalleExtraDTO> toDTOList(List<DetalleExtra> entities){
        return entities.stream().map(entity -> toDTO(entity)).toList();
    }
}