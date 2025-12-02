package com.gestaoentregas.excecoes;

public class INException extends Exception {
    public INException() {
        super("ID não existe.");
    }

    public INException(String mensagem) {
        super(mensagem);
    }

    public INException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
