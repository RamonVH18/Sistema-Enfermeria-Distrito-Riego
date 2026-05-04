package mapper;

import dtos.RegistroMedicoDTO;
import entidades.RegistroMedico;
import java.util.List;
import response.SignosVitalesResponse;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class RegistroMedicoMapper {
    
    public static SignosVitalesResponse toSignosVitalesResponse(RegistroMedico r) {
        Float imc = (float) (r.getPeso() / Math.pow(r.getAltura(), 2));
        return new SignosVitalesResponse(
                r.getAltura(),
                r.getPeso(), 
                r.getPresionSistolica(), 
                r.getPresionDiatolica(), 
                r.getGlucosa(), 
                r.getOxigenacion(), 
                imc,
                r.getFrecuenciaCardiaca(),
                r.getTemperatura()
        );
    }
}