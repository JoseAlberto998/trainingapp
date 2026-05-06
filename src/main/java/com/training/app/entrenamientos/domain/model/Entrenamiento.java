package com.training.app.entrenamientos.domain.model;


import com.training.app.entrenamientos.domain.model.exception.OrdenDuplicadoException;
import com.training.app.entrenamientos.domain.model.vo.DetalleEjercicioId;
import com.training.app.entrenamientos.domain.model.vo.EntrenamientoDetalleId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entrenamiento {

    private final EntrenamientoDetalleId id;
    private final String nombre;
    private final int orden;
    private final String notas;
    private final List<Ejercicio> ejercicios;

    private Entrenamiento(EntrenamientoDetalleId id,
                                  String nombre,
                                  int orden,
                                  String notas,
                                  List<Ejercicio> ejercicios) {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
        this.notas = notas;
        this.ejercicios = new ArrayList<>(ejercicios);
    }

    // Nuevo — sin id
    public static Entrenamiento of(String nombre, int orden, String notas) {
        validar(nombre, orden);
        return new Entrenamiento(null, nombre, orden, notas, new ArrayList<>());
    }

    // Reconstruir desde bbdd — con id y ejercicios ya cargados
    public static Entrenamiento reconstitute(EntrenamientoDetalleId id,
                                                     String nombre,
                                                     int orden,
                                                     String notas,
                                                     List<Ejercicio> ejercicios) {
        validar(nombre, orden);
        return new Entrenamiento(id, nombre, orden, notas, ejercicios);
    }

    private static void validar(String nombre, int orden) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre del bloque es obligatorio");
        if (orden < 1)
            throw new IllegalArgumentException("El orden debe ser mayor que 0");
    }

    // El bloque controla cómo se añaden ejercicios
    public void agregarEjercicio(Ejercicio ejercicio) {
        if (ejercicios.stream().anyMatch(e -> e.getOrden() == ejercicio.getOrden())) {
            throw new OrdenDuplicadoException(
                "Ya existe un ejercicio con orden " + ejercicio.getOrden() + " en el bloque " + nombre
            );
        }
        ejercicios.add(ejercicio);
    }

    public void eliminarEjercicio(DetalleEjercicioId ejercicioId) {
        ejercicios.removeIf(e -> ejercicioId.equals(e.getId()));
    }

    // Lista inmutable hacia fuera
    public List<Ejercicio> getEjercicios() {
        return Collections.unmodifiableList(ejercicios);
    }

    public EntrenamientoDetalleId getId() { return id; }
    public String getNombre()             { return nombre; }
    public int getOrden()                 { return orden; }
    public String getNotas()             { return notas; }
}
