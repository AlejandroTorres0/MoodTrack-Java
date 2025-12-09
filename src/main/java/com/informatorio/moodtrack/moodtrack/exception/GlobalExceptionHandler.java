package com.informatorio.moodtrack.moodtrack.exception;

import com.informatorio.moodtrack.moodtrack.dto.errores.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex
            ,HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validacion fallida")
                .message("Uno o mas campos no son validos")
                .path(request.getRequestURI())
                .validationErrors(errors)
                .build();

        log.warn("Error de validacion en {} : {}", request.getRequestURI(), errors);

        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException ex
            ,HttpServletRequest request) {
        String specificMessage = "Error de integridad de datos";
        String details = "Asegúrate de que los campos con restricción de unicidad (como Email) no estén duplicados.";

        if (ex.getCause() != null && ex.getCause().getMessage().contains("Unique index or primary key violation")) {
            specificMessage = "Violación de Unicidad";

            String dbErrorDetails = ex.getCause().getMessage();

            if (dbErrorDetails.contains("EMAIL")) {
                details = "El Email proporcionado ya está registrado. Por favor, utiliza uno diferente.";
            } else {
                details = "El valor para un campo único (ej. ID o Email) ya existe.";
            }
        }

        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(specificMessage)
                .message(details)
                .path(request.getRequestURI())
                .validationErrors(null)
                .build();

        log.warn("Error de Integridad de Datos en {} : {}", request.getRequestURI(), ex.getMessage());

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
}
