package com.mlg.GestorTareas.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TareaDTO {
    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;
    private String estado;

    //Constructores
    public TareaDTO(){}

    public TareaDTO(String titulo, String descripcion, String estado){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public TareaDTO(Long id, String titulo, String descripcion, String estado){
        this(titulo, descripcion, estado);
        this.id = id;
    }
}