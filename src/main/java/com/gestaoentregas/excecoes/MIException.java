package com.gestaoentregas.excecoes;

public class MIException extends Exception {

    public MIException() {
        super("Motorista indisponível.");
    }

    public MIException(String mensagem) {
        super(mensagem);
    }

    public MIException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
