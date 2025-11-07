package com.mlg.GestorTareas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<?> manejadorRecursoNoEncontrado(RecursoNoEncontradoException ex){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", ex.getMessage());
        mensaje.put("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    }
}
