package mapper;

import dtos.RegistroMedicoDTO;
import entidades.RegistroMedico;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class RegistroMedicoMapper {
    
    public static  RegistroMedico toEntity( RegistroMedicoDTO dto){
        RegistroMedico entity = new  RegistroMedico();
        entity.setId(dto.getId());
        
        return entity;
    }
    
    public static  RegistroMedicoDTO toDTO( RegistroMedico entity){
        RegistroMedicoDTO dto = new  RegistroMedicoDTO();
        dto.setId(entity.getId());
        
        return dto;
    }
    
    public static List< RegistroMedico> toEntityList(List<RegistroMedicoDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).toList();
    }
    
    public static List< RegistroMedicoDTO> toDTOList(List<RegistroMedico> entities){
        return entities.stream().map(entity -> toDTO(entity)).toList();
    }
}