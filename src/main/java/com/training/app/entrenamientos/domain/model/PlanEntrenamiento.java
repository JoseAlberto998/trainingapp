package com.training.app.entrenamientos.domain.model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.training.app.entrenamientos.domain.model.exception.EntrenamientoNoValidoException;
import com.training.app.entrenamientos.domain.model.exception.OrdenDuplicadoException;

public class PlanEntrenamiento {

    private final UUID id;
    private final UUID idusuario;
    private final String nombrePlan;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private boolean activo;
    private final List<Entrenamiento> entrenamientos;
    
    
    private PlanEntrenamiento(UUID id,
    		UUID atletaId,
                           String nombre,
                           LocalDate fechaInicio,
                           LocalDate fechaFin,
                           boolean activo,
                           List<Entrenamiento> detalles) {
        this.id = id;
        this.idusuario = atletaId;
        this.nombrePlan = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
        this.entrenamientos = new ArrayList<>(detalles);
    }

    // Nuevo — sin id, lo genera PostgreSQL
    public static PlanEntrenamiento of(UUID creadorId,
    		UUID atletaId,
                                    String nombre,
                                    LocalDate fechaInicio,
                                    LocalDate fechaFin) {
        validar(creadorId, atletaId, nombre, fechaInicio, fechaFin);
        PlanEntrenamiento entrenamiento = new PlanEntrenamiento(
                null, atletaId, nombre, fechaInicio, fechaFin, true, new ArrayList<>()
        );

        return entrenamiento;
    }

    // Reconstruir desde bbdd — con id y detalles ya cargados
    public static PlanEntrenamiento reconstitute(UUID id,
    		UUID creadorId,
    		UUID atletaId,
                                              String nombre,
                                              LocalDate fechaInicio,
                                              LocalDate fechaFin,
                                              boolean activo,
                                              List<Entrenamiento> detalles) {
        validar(creadorId, atletaId, nombre, fechaInicio, fechaFin);
        return new PlanEntrenamiento(id, atletaId, nombre, fechaInicio, fechaFin, activo, detalles);
    }

    private static void validar(UUID creadorId,
    		UUID atletaId,
                                  String nombre,
                                  LocalDate fechaInicio,
                                  LocalDate fechaFin) {
        if (creadorId == null)
            throw new EntrenamientoNoValidoException("El creador es obligatorio");
        if (atletaId == null)
            throw new EntrenamientoNoValidoException("El atleta es obligatorio");
        if (nombre == null || nombre.isBlank())
            throw new EntrenamientoNoValidoException("El nombre es obligatorio");
        if (fechaInicio == null)
            throw new EntrenamientoNoValidoException("La fecha de inicio es obligatoria");
        if (fechaFin == null)
            throw new EntrenamientoNoValidoException("La fecha de fin es obligatoria");
        if (!fechaFin.isAfter(fechaInicio))
            throw new EntrenamientoNoValidoException("La fecha fin debe ser posterior a la fecha inicio");
    }

    // El agregado raíz controla cómo se añaden los bloques
    public void agregarDetalle(Entrenamiento nuevoEntrenamiento) {
    	//FIXME: Por que se hace esto?
        if (entrenamientos.stream().anyMatch(entrenamiento -> entrenamiento.getOrden() == nuevoEntrenamiento.getOrden())) {
            throw new OrdenDuplicadoException(
                "Ya existe un bloque con orden " + nuevoEntrenamiento.getOrden() + " en el entrenamiento " + nombrePlan
            );
        }
        entrenamientos.add(nuevoEntrenamiento);
    }

    public void eliminarDetalle(UUID entrenamientoId) {
        entrenamientos.removeIf(entrenamiento -> entrenamientoId.equals(entrenamiento.getId()));
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }


    // Lista inmutable hacia fuera
    public List<Entrenamiento> getDetalles() {
        return Collections.unmodifiableList(entrenamientos);
    }

  
}
