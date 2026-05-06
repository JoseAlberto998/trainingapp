package com.training.app.entrenamientos.domain.model.exception;

public class EntrenamientoNoValidoException extends RuntimeException {
    public EntrenamientoNoValidoException(String mensaje) {
        super(mensaje);
    }
}
