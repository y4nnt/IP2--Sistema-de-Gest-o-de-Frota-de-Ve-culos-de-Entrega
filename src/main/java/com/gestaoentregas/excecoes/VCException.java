package com.gestaoentregas.excecoes;

public class VCException extends Exception {

    public VCException() {
        super("Veículo já cadastrado.");
    }

    public VCException(String mensagem) {
        super(mensagem);
    }

    public VCException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
