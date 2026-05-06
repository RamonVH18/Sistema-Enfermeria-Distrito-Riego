package interfaces;

import response.UsuarioResponse;

/**
 *
 * @author isaac
 */
public interface IServicioUsuarios {
    public UsuarioResponse login(String email, String password);
}
