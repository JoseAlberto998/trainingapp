package com.training.app.entrenamientos.domain.model;

import java.util.Objects;
import java.util.UUID;

import com.training.app.entrenamientos.domain.model.vo.PesoObjetivo;

public class Ejercicio {

	private final UUID id;
	private final int orden;
	private final int series;
	private final int repeticiones;
	private final PesoObjetivo pesoObjetivo;
	private final String notas;

	public Ejercicio(UUID id, int orden, int series, int repeticiones, PesoObjetivo pesoObjetivo, String notas) {
		validar(id, orden, series, repeticiones);
		this.id = id;
		this.orden = orden;
		this.series = series;
		this.repeticiones = repeticiones;
		this.pesoObjetivo = pesoObjetivo;
		this.notas = notas;
	}
	
	// Sin ID
	public Ejercicio(int orden, int series, int repeticiones, PesoObjetivo pesoObjetivo, String notas) {
		//Creo UUID
		this.id = UUID.randomUUID();
		validar(id, orden, series, repeticiones);		
		this.orden = orden;
		this.series = series;
		this.repeticiones = repeticiones;
		this.pesoObjetivo = pesoObjetivo;
		this.notas = notas;
	}


	private static void validar(UUID ejercicioId, int orden, int series, int repeticiones) {
		if (ejercicioId == null)
			throw new IllegalArgumentException("El ejercicio es obligatorio");
		if (orden < 1)
			throw new IllegalArgumentException("El orden debe ser mayor que 0");
		if (series < 1)
			throw new IllegalArgumentException("Las series deben ser al menos 1");
		if (repeticiones < 1)
			throw new IllegalArgumentException("Las repeticiones deben ser al menos 1");
	}

	public UUID getId() {
		return id;
	}

	public int getOrden() {
		return orden;
	}

	public int getSeries() {
		return series;
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public PesoObjetivo getPesoObjetivo() {
		return pesoObjetivo;
	}

	public String getNotas() {
		return notas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, notas, orden, pesoObjetivo, repeticiones, series);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ejercicio)) {
			return false;
		}
		Ejercicio other = (Ejercicio) obj;
		return Objects.equals(id, other.id) && Objects.equals(notas, other.notas) && orden == other.orden
				&& Objects.equals(pesoObjetivo, other.pesoObjetivo) && repeticiones == other.repeticiones
				&& series == other.series;
	}
	
	

}
