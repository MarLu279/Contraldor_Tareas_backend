package com.mlg.GestorTareas.service;

import com.mlg.GestorTareas.exception.RecursoNoEncontradoException;
import com.mlg.GestorTareas.model.dto.TareaDTO;
import com.mlg.GestorTareas.model.entity.Tarea;
import com.mlg.GestorTareas.repository.TareaRespositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TareaServicio {
    private final TareaRespositorio tareaRepositorio;


    public TareaServicio(TareaRespositorio tareaRepositorio) {
        this.tareaRepositorio = tareaRepositorio;
    }

    public TareaDTO crearTarea(TareaDTO tareaDto){
        Tarea tarea = convertirDtoEntidad(tareaDto);
        Tarea tareaGuardada = tareaRepositorio.save(tarea);
        return convertirEntidadDto(tareaGuardada);
    }

    public List<TareaDTO> listarTareas(){
        List<Tarea> tareasEntidad = tareaRepositorio.findAll();
        List<TareaDTO> tareas = new ArrayList<>();
        for(Tarea tarea : tareasEntidad){
            tareas.add(convertirEntidadDto(tarea));
        }
        return tareas;
    }

    public TareaDTO buscarTareaId(Long id){
        TareaDTO tareaEncontrada = null;
        Optional<Tarea> tareaOptional = tareaRepositorio.findById(id);
        if(tareaOptional.isPresent()){
            tareaEncontrada = convertirEntidadDto(tareaOptional.get());
            return tareaEncontrada;
        } else {
            throw new RecursoNoEncontradoException("Tarea con ID: " + id + " no encontrada");
        }
    }

    //Métodos complementarios
    private Tarea convertirDtoEntidad(TareaDTO tDto){
        Tarea tarea = new Tarea();
        tarea.setTitulo(tDto.getTitulo());
        tarea.setDescripcion(tDto.getDescripcion());
        tarea.setEstado(tDto.getEstado());
        return tarea;
    }

    private TareaDTO convertirEntidadDto(Tarea tarea){
        TareaDTO tareaDto = new TareaDTO();
        tareaDto.setId(tarea.getId());
        tareaDto.setTitulo(tarea.getTitulo());
        tareaDto.setDescripcion(tarea.getDescripcion());
        tareaDto.setEstado(tarea.getEstado());
        return tareaDto;
    }
}