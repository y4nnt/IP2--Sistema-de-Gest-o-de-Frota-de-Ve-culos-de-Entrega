package com.gestaoentregas.excecoes;

public class CNHException extends RuntimeException {
    public CNHException() {
        super("CNH Inválida");
    }

    public CNHException(String message) {
        super(message);
    }

    public CNHException(String message, Throwable cause) {
        super(message, cause);
    }
}