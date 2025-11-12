package com.gestaoentregas.excecoes;

public class DIException extends RuntimeException {
    public DIException() {
        super("Dado inválido.");
    }

    public DIException(String message) {
        super(message);
    }

    public  DIException(String message, Throwable cause) {
        super(message, cause);
    }
}
