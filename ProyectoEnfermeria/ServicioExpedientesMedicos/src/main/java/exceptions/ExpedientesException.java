/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

import org.springframework.http.HttpStatus;
import webExceptions.BusinessException;

/**
 *
 * @author Ramon Valencia
 */
public class ExpedientesException extends BusinessException {

    /**
     * Creates a new instance of <code>CitasException</code> without detail
     * message.
     *
     * @param mensaje
     * @param status
     * @param codigo
     */
    public ExpedientesException(String mensaje, HttpStatus status, String codigo) {
        super(mensaje, status, codigo);
    }

}
