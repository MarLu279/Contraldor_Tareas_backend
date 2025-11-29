package com.mlg.GestorTareas.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorRespuesta {
    private String mensaje;
    private int status;
    private String path;
    private LocalDateTime hora;
}
