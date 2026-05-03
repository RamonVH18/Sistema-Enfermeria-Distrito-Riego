/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import org.springframework.http.HttpStatus;
import webExceptions.BusinessException;

/**
 *
 * @author isaac
 */
public class ExpedienteException extends BusinessException {

    /**
     * Creates a new instance of <code>ExpedienteException</code> without detail
     * message.
     *
     * @param mensaje
     * @param status
     * @param codigo
     */
    public ExpedienteException(String mensaje, HttpStatus status, String codigo) {
        super(mensaje, status, codigo);
    }

    /**
     * Constructs an instance of <code>ExpedienteException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
}
