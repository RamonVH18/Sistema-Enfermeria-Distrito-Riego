/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webExceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Ramon Valencia
 */
@Getter
public abstract class BusinessException extends RuntimeException {
    private final HttpStatus status;
    private final String codigo;

    protected BusinessException(String mensaje, HttpStatus status, String codigo) {
        super(mensaje);
        this.status = status;
        this.codigo = codigo;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCodigo() {
        return codigo;
    }
    
}
