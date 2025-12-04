package com.gestaoentregas.excecoes;

public class UIException extends RuntimeException {
    public UIException() {super("Usuário indisponível ou inválido.");}

    public UIException(String message) {
        super(message);
    }

    public UIException(String message, Throwable cause) {}
}
