package manejo;

import dtos.ErrorResponse;
import webExceptions.BusinessException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Ramon Valencia
 */
@RestControllerAdvice
public class ManejadorExcepcionesWeb {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getCodigo(),
                ex.getMessage(),
                ex.getStatus().value(),
                LocalDateTime.now(),
                null
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    // 2. Atrapa errores de validación (@Valid, @NotBlank, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erroresCampos = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err
                -> erroresCampos.put(err.getField(), err.getDefaultMessage()));

        ErrorResponse error = new ErrorResponse(
                "ERROR_VALIDACION",
                "Los datos enviados no son válidos",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                erroresCampos
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}