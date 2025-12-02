package com.gestaoentregas.excecoes;

public class INEException extends Exception {
    public INEException() {
        super("ID não existe.");
    }

    public INEException(String mensagem) {
        super(mensagem);
    }

    public INEException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
