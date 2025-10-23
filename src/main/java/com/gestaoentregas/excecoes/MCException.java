package com.gestaoentregas.excecoes;

public class MCException extends Exception {

    public MCException() {
        super("Motorista já cadastrado.");
    }

    public MCException(String mensagem) {
        super(mensagem);
    }

    public  MCException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
