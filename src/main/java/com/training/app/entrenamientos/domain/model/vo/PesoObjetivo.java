package com.training.app.entrenamientos.domain.model.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class PesoObjetivo {

    private final BigDecimal valor;

    public PesoObjetivo(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El peso objetivo no puede ser negativo");
        }
        this.valor = valor;
    }

    public static PesoObjetivo of(BigDecimal valor) {
        return new PesoObjetivo(valor);
    }

    public static PesoObjetivo vacio() {
        return new PesoObjetivo(null);
    }

    public boolean tieneValor() {
        return valor != null;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PesoObjetivo)) return false;
        PesoObjetivo that = (PesoObjetivo) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor != null ? valor + " kg" : "sin peso definido";
    }
}
