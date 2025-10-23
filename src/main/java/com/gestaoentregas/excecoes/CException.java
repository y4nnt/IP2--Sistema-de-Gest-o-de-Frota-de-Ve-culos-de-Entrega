package com.gestaoentregas.excecoes;

public class CException extends Exception {

    public CException() {
        super("Associação já cadastrada.");
    }

    public CException(String mensagem) {
        super(mensagem);
    }

    public CException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }


}
