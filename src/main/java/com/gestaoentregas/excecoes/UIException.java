package com.gestaoentregas.excecoes;

public class UIException extends Exception {
    public UIException() {
        super("Usuário inexistente.");
    }

    public UIException(String mensagem) {
        super(mensagem);
    }

    public UIException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
