package com.gestaoentregas.excecoes;

public class UCException extends Exception {
    public UCException() {
        super("Usuário já cadastrado.");
    }

    public UCException(String mensagem) {
        super(mensagem);
    }

    public UCException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
