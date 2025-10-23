package com.gestaoentregas.excecoes;

public class PIException extends RuntimeException {
    public PIException() {
        super("Produto indisponível.");
    }

    public PIException(String mensagem) {
        super(mensagem);
    }

    public PIException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
