package mapper;

import dtos.AntecedenteDTO;
import entidades.Antecedente;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class AntecedenteMapper {
    
    public static Antecedente toEntity(AntecedenteDTO dto){
        Antecedente entity = new Antecedente();
        entity.setId(dto.getId());
        entity.setAntecedente(dto.getAntecedente());
        entity.setTipo(dto.getTipo());
        return entity;
    }
    
    public static AntecedenteDTO toDTO(Antecedente entity){
        AntecedenteDTO dto = new AntecedenteDTO();
        dto.setId(entity.getId());
        dto.setAntecedente(entity.getAntecedente());
        dto.setTipo(entity.getTipo());
        return dto;
    }
    
    public static List<Antecedente> toEntityList(List<AntecedenteDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).toList();
    }
    
    public static List<AntecedenteDTO> toDTOList(List<Antecedente> entities){
        return entities.stream().map(entity -> toDTO(entity)).toList();
    }
}