package com.mlg.GestorTareas.service;

import com.mlg.GestorTareas.exception.RecursoNoEncontradoException;
import com.mlg.GestorTareas.model.dto.TareaDTO;
import com.mlg.GestorTareas.model.entity.Tarea;
import com.mlg.GestorTareas.repository.TareaRespositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    //Eliminar tarea
    public void eliminarTarea(Long id){
        if(!tareaRepositorio.existsById(id)){
            throw new RecursoNoEncontradoException("Tarea no encontrada");
        }
        tareaRepositorio.deleteById(id);
    }

    public TareaDTO actualizarTareaParcial(Long id, Map<String, Object> camposActualizar){
        Optional<Tarea> tareaExistente = tareaRepositorio.findById(id);

       if(tareaExistente.isEmpty()){
           throw new RecursoNoEncontradoException("Tarea con ID: " + id + " no encontrada");
       }
       Tarea tareaEncontrada = tareaExistente.get();

        //Actualizar campos recibidos
        if(camposActualizar.containsKey("titulo")){
            tareaEncontrada.setTitulo((String) camposActualizar.get("titulo"));
        }
        if (camposActualizar.containsKey("descripcion")){
            tareaEncontrada.setDescripcion((String) camposActualizar.get("descripcion"));
        }
        if(camposActualizar.containsKey("estado")){
            tareaEncontrada.setEstado((Boolean) camposActualizar.get("estado"));
        }
        //Guardar cambios
        Tarea tareaActualizada = tareaRepositorio.save(tareaEncontrada);
        return convertirEntidadDto(tareaActualizada);
    }

    public TareaDTO marcarComoCompletada(Long id){
        Optional<Tarea> tareaEncontrada = tareaRepositorio.findById(id);

        if(tareaEncontrada.isEmpty()){
            throw new RecursoNoEncontradoException("Tarea con ID: " + id + " no encontrada");
        }
        Tarea tareaCompletada = tareaEncontrada.get();
        tareaCompletada.setEstado(true);
        Tarea tareaActualizada = tareaRepositorio.save(tareaCompletada);
        return convertirEntidadDto(tareaActualizada);
    }

    //Métodos complementarios
    private Tarea convertirDtoEntidad(TareaDTO tDto){
        Tarea tarea = new Tarea();
        tarea.setTitulo(tDto.getTitulo());
        tarea.setDescripcion(tDto.getDescripcion());
        tarea.setEstado(tDto.isEstado());
        return tarea;
    }

    private TareaDTO convertirEntidadDto(Tarea tarea){
        TareaDTO tareaDto = new TareaDTO();
        tareaDto.setId(tarea.getId());
        tareaDto.setTitulo(tarea.getTitulo());
        tareaDto.setDescripcion(tarea.getDescripcion());
        tareaDto.setEstado(tarea.isEstado());
        return tareaDto;
    }
}