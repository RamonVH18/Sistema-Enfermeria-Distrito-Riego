package exception;

import org.springframework.http.HttpStatus;
import webExceptions.BusinessException;

/**
 *
 * @author Leonardo Flores Leyva - 00000252390
 */
public class UsuarioException extends BusinessException  {

    /**
     * Creates a new instance of <code>CitasException</code> without detail
     * message.
     * @param mensaje
     * @param status
     * @param codigo
     */

    public UsuarioException(String mensaje, HttpStatus status, String codigo) {
        super(mensaje, status, codigo);
    }

    /**
     * Constructs an instance of <code>CitasException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    
}