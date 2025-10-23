package com.gestaoentregas.excecoes;

public class ENCException extends RuntimeException {
    public ENCException() {
        super("Não é possível cadastrar essa entrega.");
    }

    public ENCException(String mensagem) {
        super(mensagem);
    }

    public ENCException(String mensagem, Throwable cause){
        super(mensagem, cause);
    }
}
