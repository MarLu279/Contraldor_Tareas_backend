package com.mlg.GestorTareas.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControladorGlobalExcepciones {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespuesta> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                         HttpServletRequest request){
        String mensaje = "ERROR";

        if(!ex.getBindingResult().getFieldErrors().isEmpty()){
            //Recupera solo errores de campos
            FieldError error = ex.getBindingResult().getFieldErrors().get(0);
            mensaje = error.getDefaultMessage();
        }
        ErrorRespuesta errorAtributos = ErrorRespuesta.builder()
                .mensaje(mensaje)
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .hora(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(errorAtributos);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorRespuesta> handleRecursoNoEncontrado(RecursoNoEncontradoException ex, HttpServletRequest request){
        ErrorRespuesta error = ErrorRespuesta.builder()
                .mensaje(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .hora(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
