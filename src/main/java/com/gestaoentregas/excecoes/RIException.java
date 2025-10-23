package com.gestaoentregas.excecoes;

public class RIException extends RuntimeException {
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
