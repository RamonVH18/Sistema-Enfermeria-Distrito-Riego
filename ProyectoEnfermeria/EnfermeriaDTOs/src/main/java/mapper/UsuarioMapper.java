package mapper;

import entidades.Empleado;
import entidades.Usuario;
import response.UsuarioResponse;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class UsuarioMapper {

    public static UsuarioResponse toOptionResponse(Usuario u) {

        Empleado e = u.getEmpleado();

        return new UsuarioResponse(
                e.getNombres()
                + " " + e.getApellidoPaterno()
                + " " + e.getApellidoMaterno(),
                u.getEmail()
        );
    }

}