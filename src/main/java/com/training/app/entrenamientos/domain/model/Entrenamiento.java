package com.training.app.entrenamientos.domain.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.training.app.entrenamientos.domain.model.exception.OrdenDuplicadoException;

public class Entrenamiento {

    private final UUID id;
    private final String nombre;
    private final int orden;
    private final String notas;
    private final List<Ejercicio> ejercicios;

    private Entrenamiento(UUID id,
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
    public static Entrenamiento reconstitute(UUID id,
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
    	//FIXME: Este metodo para que requiere tanta fiesta? deberia agregarse sin mas
        if (ejercicios.stream().anyMatch(e -> e.getOrden() == ejercicio.getOrden())) {
            throw new OrdenDuplicadoException(
                "Ya existe un ejercicio con orden " + ejercicio.getOrden() + " en el bloque " + nombre
            );
        }
        ejercicios.add(ejercicio);
    }

    public void eliminarEjercicio(UUID ejercicioId) {
        ejercicios.removeIf(ejercicio -> ejercicioId.equals(ejercicio.getId()));
    }

    // Lista inmutable hacia fuera
    public List<Ejercicio> getEjercicios() {
        return Collections.unmodifiableList(ejercicios);
    }

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getOrden() {
		return orden;
	}

	public String getNotas() {
		return notas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ejercicios, id, nombre, notas, orden);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Entrenamiento)) {
			return false;
		}
		Entrenamiento other = (Entrenamiento) obj;
		return Objects.equals(ejercicios, other.ejercicios) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(notas, other.notas) && orden == other.orden;
	}

  
}
