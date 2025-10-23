package com.gestaoentregas.excecoes;

public class CIException extends Exception {
    public CIException() {
        super("Asssociação inexistente.");
    }

    public CIException(String message) {
        super(message);
    }

    public CIException(String message, Throwable cause) {
        super(message, cause);
    }
}
