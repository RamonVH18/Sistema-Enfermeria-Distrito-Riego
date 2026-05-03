package mapper;

import dtos.DepartamentoDTO;
import entidades.Departamento;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class DepartamentoMapper {
    
    public static Departamento toEntity( DepartamentoDTO dto){
        Departamento entity = new  Departamento();
        entity.setId(dto.getIdJefeDepartamento());
        entity.setNombre(dto.getNombre());
        
        return entity;
    }
    
    public static DepartamentoDTO toDTO( Departamento entity){
        DepartamentoDTO dto = new  DepartamentoDTO();
        dto.setIdJefeDepartamento(entity.getId());
        dto.setNombre(entity.getNombre());
        
        return dto;
    }
    
    public static List<Departamento> toEntityList(List< DepartamentoDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).toList();
    }
    
    public static List<DepartamentoDTO> toDTOList(List< Departamento> entities){
        return entities.stream().map(entity -> toDTO(entity)).toList();
    }
}