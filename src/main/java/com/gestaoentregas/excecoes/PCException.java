package com.gestaoentregas.excecoes;

public class PCException extends RuntimeException {
    public PCException() {
        super("Produto já cadastrado.");
    }

    public PCException(String mensagem) {
        super(mensagem);
    }

    public PCException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
