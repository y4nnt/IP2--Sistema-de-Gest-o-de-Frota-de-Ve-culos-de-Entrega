package com.gestaoentregas.excecoes;

public class IIException extends RuntimeException {
    public IIException() {
        super("Motorista menor de idade.");
    }

    public IIException(String message) {
        super(message);
    }

    public IIException(String message, Throwable cause) {
        super(message, cause);
    }
}
