package com.mlg.GestorTareas.repository;

import com.mlg.GestorTareas.model.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRespositorio extends JpaRepository<Tarea, Long> {
}
