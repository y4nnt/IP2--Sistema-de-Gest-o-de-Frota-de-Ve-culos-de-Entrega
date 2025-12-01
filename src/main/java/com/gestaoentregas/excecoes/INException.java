package com.gestaoentregas.excecoes;

public class IEException extends Exception {
    public IEException() {
        super("ID não existe.");
    }

    public IEException(String mensagem) {
        super(mensagem);
    }

    public IEException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
