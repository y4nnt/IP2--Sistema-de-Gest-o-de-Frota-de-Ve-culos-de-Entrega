package com.gestaoentregas.excecoes;

public class RIException extends Exception {
    public RIException() {
        super("Rota indisponível.");
    }

    public RIException(String mensagem) {
        super(mensagem);
    }

    public RIException(String mensagem, Throwable cause){
        super(mensagem, cause);
    }
}
