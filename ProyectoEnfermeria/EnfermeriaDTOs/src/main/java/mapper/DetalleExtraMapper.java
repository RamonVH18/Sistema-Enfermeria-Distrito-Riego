package mapper;

import dtos.DetalleExtraDTO;
import entidades.Detalle;
import entidades.DetalleExtra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import response.DetalleResponse;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class DetalleExtraMapper {
    
    public static Map<String, List<DetalleResponse>> toDetalleResponseMap(List<DetalleExtra> detalles) {
    Map<String, List<DetalleResponse>> map = new HashMap<>();

    for (DetalleExtra d : detalles) {
        String nombre = d.getDetalle().getNombreDetalle();
        
        // Creamos el DTO de respuesta para este detalle
        DetalleResponse response = new DetalleResponse(d.getDetalle().getNombreDetalle(), d.getValor());

        // Si la llave no existe, computeIfAbsent crea la lista vacía por nosotros
        map.computeIfAbsent(nombre, k -> new ArrayList<>()).add(response);
    }
    
    return map;
}
}