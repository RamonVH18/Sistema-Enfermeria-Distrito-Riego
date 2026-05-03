package mapper;

import dtos.DetalleExtraDTO;
import dtos.DetalleExtraExpedienteMedicoDTO;
import dtos.ExpedienteMedicoDTO;
import entidades.DetalleExtra;
import entidades.DetalleExtraExpedienteMedico;
import entidades.ExpedienteMedico;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class DetalleExtraExpedienteMedicoMapper {
    
    public static DetalleExtraExpedienteMedico toEntity(DetalleExtraExpedienteMedicoDTO dto){
        DetalleExtraExpedienteMedico entity = new  DetalleExtraExpedienteMedico();
        entity.setId(dto.getId());
        entity.setValor(dto.getValor());
        
        DetalleExtraDTO detalleExtraDTO = dto.getDetalleExtra();
        entity.setDetalleExtra((detalleExtraDTO != null) ? DetalleExtraMapper.toEntity(detalleExtraDTO) : null);
        
        ExpedienteMedicoDTO expedienteMedicoDTO = dto.getExpedienteMedico();
        ExpedienteMedico expedienteMedico = new ExpedienteMedico();
        expedienteMedico.setId((expedienteMedicoDTO != null) ? expedienteMedicoDTO.getId() : null);
        entity.setExpedienteMedico(expedienteMedico);
        
        return entity;
    }
    
    public static DetalleExtraExpedienteMedicoDTO toDTO(DetalleExtraExpedienteMedico entity){
        DetalleExtraExpedienteMedicoDTO dto = new  DetalleExtraExpedienteMedicoDTO();
        dto.setId(entity.getId());
        dto.setValor(entity.getValor());
        
        DetalleExtra detalleExtra = entity.getDetalleExtra();
        dto.setDetalleExtra((detalleExtra != null) ? DetalleExtraMapper.toDTO(detalleExtra) : null);
        
        ExpedienteMedico expedienteMedico = entity.getExpedienteMedico();
        ExpedienteMedicoDTO expedienteMedicoDTO = new ExpedienteMedicoDTO();
        expedienteMedicoDTO.setId((expedienteMedico != null) ? expedienteMedico.getId() : null);
        dto.setExpedienteMedico(expedienteMedicoDTO);
        
        return dto;
    }
    
    public static Set<DetalleExtraExpedienteMedico> toEntitySet(Set<DetalleExtraExpedienteMedicoDTO> dtos){
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toSet());
    }
    
    public static Set<DetalleExtraExpedienteMedicoDTO> toDTOSet(Set<DetalleExtraExpedienteMedico> entities){
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toSet());
    }
}