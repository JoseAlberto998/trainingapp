package com.training.app.entrenamientos.domain.model.vo;

import java.util.Objects;
import java.util.UUID;

public class DetalleEjercicioId {

    private final UUID valor;

    public DetalleEjercicioId(UUID valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El id del detalle de ejercicio no puede ser null");
        }
        this.valor = valor;
    }

    public static DetalleEjercicioId of(String valor) {
        try {
            return new DetalleEjercicioId(UUID.fromString(valor));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El id del detalle no tiene formato UUID válido: " + valor);
        }
    }

    public UUID getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleEjercicioId)) return false;
        DetalleEjercicioId that = (DetalleEjercicioId) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor.toString();
    }
}
