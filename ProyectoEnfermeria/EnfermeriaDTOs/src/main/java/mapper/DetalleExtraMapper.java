package mapper;

import entidades.DetalleExtra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import response.AntecedenteResponse;

/**
 *
 * @author Ramon Valencia Hernandez
 */
public class DetalleExtraMapper {

    public static Map<String, List<AntecedenteResponse>> toDetalleResponseMap(List<DetalleExtra> detalles) {
        Map<String, List<AntecedenteResponse>> map = new HashMap<>();

        for (DetalleExtra d : detalles) {
            String nombre = d.getDetalle().getNombreDetalle();

            // Creamos el DTO de respuesta para este detalle
            AntecedenteResponse response = new AntecedenteResponse(d.getDetalle().getNombreDetalle(), d.getValor());

            // Si la llave no existe, computeIfAbsent crea la lista vacía por nosotros
            map.computeIfAbsent(nombre, k -> new ArrayList<>()).add(response);
        }

        return map;
    }
}
