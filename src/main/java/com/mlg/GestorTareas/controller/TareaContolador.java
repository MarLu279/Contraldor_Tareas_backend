package com.mlg.GestorTareas.controller;

import com.mlg.GestorTareas.model.dto.TareaDTO;
import com.mlg.GestorTareas.service.TareaServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
public class TareaContolador {
    private final TareaServicio tareaServicio;

    public TareaContolador(TareaServicio tareaServicio){
        this.tareaServicio = tareaServicio;
    }

    @GetMapping
    public ResponseEntity<List<TareaDTO>> obtenerTodasLasTareas(){
        List<TareaDTO> tareas = tareaServicio.listarTareas();
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> buscarTareaId(@PathVariable Long id){
        TareaDTO tareaEncontrada = tareaServicio.buscarTareaId(id);
        return ResponseEntity.ok(tareaEncontrada);
    }

    @PostMapping
    public ResponseEntity<TareaDTO> crearTarea(@Valid @RequestBody TareaDTO tareaDTO){
        TareaDTO nuevaTarea = tareaServicio.crearTarea(tareaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id){
        tareaServicio.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }

     @PatchMapping("/{id}")
    public ResponseEntity<TareaDTO> actualizarTareaParcial (@PathVariable Long id,
                                                            @RequestBody Map<String, Object> camposActualizar){
        TareaDTO tareaActualizada = tareaServicio.actualizarTareaParcial(id, camposActualizar);
        return ResponseEntity.ok(tareaActualizada);
     }

     @PatchMapping("/{id}/completar")
    public ResponseEntity<TareaDTO> marcarComoCompletada(@PathVariable Long id){
        TareaDTO tareaCompletada = tareaServicio.marcarComoCompletada(id);
        return ResponseEntity.ok(tareaCompletada);
     }
}