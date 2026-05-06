package com.training.app.entrenamientos.domain.model.exception;

public class OrdenDuplicadoException extends RuntimeException {
    public OrdenDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
