package com.gestaoentregas.excecoes;

public class CPFException extends RuntimeException {
    public CPFException() {
        super("CPF invalido");
    }

    public CPFException(String message) {
        super(message);
    }

    public CPFException(String message, Throwable cause) {
        super(message, cause);
    }
}
