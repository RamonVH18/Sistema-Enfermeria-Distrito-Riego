package mapper;

import dtos.NuevaSerieDTO;
import dtos.SerieDTO;
import entidades.Serie;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class SerieMapper {
    
    public static Serie toEntityNew(NuevaSerieDTO dto) {
        Serie entity = new Serie();
        entity.setPeriodo(dto.getPeriodo());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setHora(dto.getHora());
        
        return entity;        
    }
    
    public static Serie toEntityOld(SerieDTO dto){
        Serie entity = new Serie();
        entity.setId(dto.getIdSerie());
        entity.setPeriodo(dto.getPeriodo());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setHora(dto.getHora());
        
        return entity;
    }
    
    public static SerieDTO toDTO(Serie entity) {
        SerieDTO dto = new SerieDTO();
        dto.setIdSerie(entity.getId());
        dto.setPeriodo(entity.getPeriodo());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaFin(entity.getFechaFin());
        dto.setHora(entity.getHora());
        
        return dto;
    }
}