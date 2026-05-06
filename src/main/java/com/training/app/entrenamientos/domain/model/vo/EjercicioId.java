package com.training.app.entrenamientos.domain.model.vo;

import java.util.Objects;
import java.util.UUID;

public class EjercicioId {

    private final UUID valor;

    public EjercicioId(UUID valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El id del ejercicio no puede ser null");
        }
        this.valor = valor;
    }

    public static EjercicioId of(String valor) {
        try {
            return new EjercicioId(UUID.fromString(valor));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El id del ejercicio no tiene formato UUID válido: " + valor);
        }
    }

    public UUID getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EjercicioId)) return false;
        EjercicioId that = (EjercicioId) o;
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
