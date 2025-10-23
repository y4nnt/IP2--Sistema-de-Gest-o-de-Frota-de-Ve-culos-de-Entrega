package com.gestaoentregas.excecoes;

public class RCException extends Exception {
    public RCException() {
        super("Rota já cadastrada.");
    }

    public RCException(String mensagem) {
        super(mensagem);
    }

    public RCException(String mensagem, Throwable cause){
        super(mensagem, cause);
    }
}
