package com.training.app.entrenamientos.domain.model;



import com.training.app.entrenamientos.domain.model.exception.EntrenamientoNoValidoException;
import com.training.app.entrenamientos.domain.model.exception.OrdenDuplicadoException;
import com.training.app.entrenamientos.domain.model.vo.EntrenamientoDetalleId;
import com.training.app.entrenamientos.domain.model.vo.EntrenamientoId;
import com.training.app.entrenamientos.domain.model.vo.UsuarioId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Plan {

    private final EntrenamientoId id;
    private final UsuarioId atletaId;
    private final String nombre;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private boolean activo;
    private final List<Entrenamiento> entrenamientos;
    
    
    private Plan(EntrenamientoId id,
                           UsuarioId atletaId,
                           String nombre,
                           LocalDate fechaInicio,
                           LocalDate fechaFin,
                           boolean activo,
                           List<Entrenamiento> detalles) {
        this.id = id;
        this.atletaId = atletaId;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
        this.entrenamientos = new ArrayList<>(detalles);
    }

    // Nuevo — sin id, lo genera PostgreSQL
    public static Plan of(UsuarioId creadorId,
                                    UsuarioId atletaId,
                                    String nombre,
                                    LocalDate fechaInicio,
                                    LocalDate fechaFin) {
        validar(creadorId, atletaId, nombre, fechaInicio, fechaFin);
        Plan entrenamiento = new Plan(
                null, atletaId, nombre, fechaInicio, fechaFin, true, new ArrayList<>()
        );

        return entrenamiento;
    }

    // Reconstruir desde bbdd — con id y detalles ya cargados
    public static Plan reconstitute(EntrenamientoId id,
                                              UsuarioId creadorId,
                                              UsuarioId atletaId,
                                              String nombre,
                                              LocalDate fechaInicio,
                                              LocalDate fechaFin,
                                              boolean activo,
                                              List<Entrenamiento> detalles) {
        validar(creadorId, atletaId, nombre, fechaInicio, fechaFin);
        return new Plan(id, atletaId, nombre, fechaInicio, fechaFin, activo, detalles);
    }

    private static void validar(UsuarioId creadorId,
                                  UsuarioId atletaId,
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
    public void agregarDetalle(Entrenamiento detalle) {
        if (entrenamientos.stream().anyMatch(d -> d.getOrden() == detalle.getOrden())) {
            throw new OrdenDuplicadoException(
                "Ya existe un bloque con orden " + detalle.getOrden() + " en el entrenamiento " + nombre
            );
        }
        entrenamientos.add(detalle);
    }

    public void eliminarDetalle(EntrenamientoDetalleId detalleId) {
        entrenamientos.removeIf(d -> detalleId.equals(d.getId()));
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

    public EntrenamientoId getId()     { return id; }
    public UsuarioId getAtletaId()     { return atletaId; }
    public String getNombre()          { return nombre; }
    public LocalDate getFechaInicio()  { return fechaInicio; }
    public LocalDate getFechaFin()     { return fechaFin; }
    public boolean isActivo()          { return activo; }
}
