package com.training.app.entrenamientos.domain.model.vo;

import java.util.Objects;
import java.util.UUID;

public class EntrenamientoId {

    private final UUID valor;

    public EntrenamientoId(UUID valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El id del entrenamiento no puede ser null");
        }
        this.valor = valor;
    }

    public static EntrenamientoId of(String valor) {
        try {
            return new EntrenamientoId(UUID.fromString(valor));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El id del entrenamiento no tiene formato UUID válido: " + valor);
        }
    }

    public UUID getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntrenamientoId)) return false;
        EntrenamientoId that = (EntrenamientoId) o;
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
