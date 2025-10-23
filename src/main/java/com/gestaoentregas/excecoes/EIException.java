package com.gestaoentregas.excecoes;

public class EIException extends Exception {
    public EIException() {
        super("Entrega indisponível.");
    }

    public EIException(String mensagem) {
        super(mensagem);
    }

    public EIException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
