package com.gestaoentregas.excecoes;

public class VInException extends RuntimeException {
    public VInException() {
        super("Preço inválido.");
    }

    public VInException(String message) {
        super(message);
    }

    public VInException(String message, Throwable cause) {
        super(message, cause);
    }
}
