package com.training.app.entrenamientos.domain.model.vo;

import java.util.Objects;
import java.util.UUID;

public class UsuarioId {

    private final UUID valor;

    public UsuarioId(UUID valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser null");
        }
        this.valor = valor;
    }

    public static UsuarioId of(String valor) {
        try {
            return new UsuarioId(UUID.fromString(valor));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El id del usuario no tiene formato UUID válido: " + valor);
        }
    }

    public UUID getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioId)) return false;
        UsuarioId that = (UsuarioId) o;
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
