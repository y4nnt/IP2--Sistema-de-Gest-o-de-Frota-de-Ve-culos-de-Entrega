package com.gestaoentregas.excecoes;

public class ECException extends Exception {
    public ECException() {
        super("Entrega já cadastrada.");
    }

    public ECException(String mensagem) {
        super(mensagem);
    }

    public ECException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
