package com.training.app.entrenamientos.domain.model;

import com.training.app.entrenamientos.domain.model.vo.DetalleEjercicioId;
import com.training.app.entrenamientos.domain.model.vo.EjercicioId;
import com.training.app.entrenamientos.domain.model.vo.PesoObjetivo;



public class Ejercicio {

    private final DetalleEjercicioId id;
    private final EjercicioId ejercicioId;
    private final int orden;
    private final int series;
    private final int repeticiones;
    private final PesoObjetivo pesoObjetivo;
    private final String notas;

    private Ejercicio(DetalleEjercicioId id,
                              EjercicioId ejercicioId,
                              int orden,
                              int series,
                              int repeticiones,
                              PesoObjetivo pesoObjetivo,
                              String notas) {
        this.id = id;
        this.ejercicioId = ejercicioId;
        this.orden = orden;
        this.series = series;
        this.repeticiones = repeticiones;
        this.pesoObjetivo = pesoObjetivo;
        this.notas = notas;
    }

    // Nuevo — sin id, PostgreSQL lo genera
    public static Ejercicio of(EjercicioId ejercicioId,
                                       int orden,
                                       int series,
                                       int repeticiones,
                                       PesoObjetivo pesoObjetivo,
                                       String notas) {
        validar(ejercicioId, orden, series, repeticiones);
        return new Ejercicio(null, ejercicioId, orden, series, repeticiones, pesoObjetivo, notas);
    }

    // Reconstruir desde bbdd — con id
    public static Ejercicio reconstitute(DetalleEjercicioId id,
                                                 EjercicioId ejercicioId,
                                                 int orden,
                                                 int series,
                                                 int repeticiones,
                                                 PesoObjetivo pesoObjetivo,
                                                 String notas) {
        validar(ejercicioId, orden, series, repeticiones);
        return new Ejercicio(id, ejercicioId, orden, series, repeticiones, pesoObjetivo, notas);
    }

    private static void validar(EjercicioId ejercicioId, int orden, int series, int repeticiones) {
        if (ejercicioId == null)
            throw new IllegalArgumentException("El ejercicio es obligatorio");
        if (orden < 1)
            throw new IllegalArgumentException("El orden debe ser mayor que 0");
        if (series < 1)
            throw new IllegalArgumentException("Las series deben ser al menos 1");
        if (repeticiones < 1)
            throw new IllegalArgumentException("Las repeticiones deben ser al menos 1");
    }

    public DetalleEjercicioId getId()     { return id; }
    public EjercicioId getEjercicioId()   { return ejercicioId; }
    public int getOrden()                  { return orden; }
    public int getSeries()                 { return series; }
    public int getRepeticiones()           { return repeticiones; }
    public PesoObjetivo getPesoObjetivo() { return pesoObjetivo; }
    public String getNotas()              { return notas; }
}
