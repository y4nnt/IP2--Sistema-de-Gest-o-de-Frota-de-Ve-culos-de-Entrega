package com.gestaoentregas.excecoes;

public class PlException extends RuntimeException {
    public PlException() {
        super("Placa Inválida.");
    }

    public PlException(String message) {
        super(message);
    }

    public  PlException(String message, Throwable cause) {
        super(message, cause);
    }
}
