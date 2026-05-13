package exception;

import org.springframework.http.HttpStatus;
import webExceptions.BusinessException;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class CitasException extends BusinessException  {
    /**
     * Creates a new instance of <code>CitasException</code> without detail
     * message.
     * @param mensaje
     * @param status
     * @param codigo
     */
    public CitasException(String mensaje, HttpStatus status, String codigo) {
        super(mensaje, status, codigo);
    }
}