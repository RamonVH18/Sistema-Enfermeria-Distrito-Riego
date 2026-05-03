package mapper;

import dtos.AntecedenteDTO;
import dtos.AntecedenteExpedienteMedicoDTO;
import dtos.ExpedienteMedicoDTO;
import entidades.Antecedente;
import entidades.AntecedenteExpedienteMedico;
import entidades.ExpedienteMedico;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class AntecedenteExpedienteMedicoMapper {
    
    public static AntecedenteExpedienteMedico toEntity(AntecedenteExpedienteMedicoDTO dto){
        AntecedenteExpedienteMedico entity = new AntecedenteExpedienteMedico();
        entity.setId(dto.getId());
        entity.setEspecificacion(dto.getEspecificacion());
        
        AntecedenteDTO antecedenteDTO = dto.getAntecedente();
        entity.setAntecedente((antecedenteDTO != null) ? AntecedenteMapper.toEntity(antecedenteDTO) : null);
        
        ExpedienteMedicoDTO expedienteMedicoDTO = dto.getExpedienteMedico();
        ExpedienteMedico expedienteMedico = new ExpedienteMedico();
        expedienteMedico.setId((expedienteMedicoDTO != null) ? expedienteMedicoDTO.getId() : null);
        entity.setExpedienteMedico(expedienteMedico);
        
        return entity;
    }
    
    public static AntecedenteExpedienteMedicoDTO toDTO(AntecedenteExpedienteMedico entity){
        AntecedenteExpedienteMedicoDTO dto = new AntecedenteExpedienteMedicoDTO();
        dto.setId(entity.getId());
        dto.setEspecificacion(entity.getEspecificacion());
        
        Antecedente antecedente = entity.getAntecedente();
        dto.setAntecedente((antecedente != null) ? AntecedenteMapper.toDTO(antecedente) : null);
        
        ExpedienteMedico expedienteMedico = entity.getExpedienteMedico();
        ExpedienteMedicoDTO expedienteMedicoDTO = new ExpedienteMedicoDTO();
        expedienteMedicoDTO.setId((expedienteMedico != null) ? expedienteMedico.getId() : null);
        dto.setExpedienteMedico(expedienteMedicoDTO);
        
        return dto;
    }
    
    public static Set<AntecedenteExpedienteMedico> toEntitySet(Set<AntecedenteExpedienteMedicoDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toSet());
    }
    
    public static Set<AntecedenteExpedienteMedicoDTO> toDTOSet(Set<AntecedenteExpedienteMedico> entities){
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toSet());
    }
}