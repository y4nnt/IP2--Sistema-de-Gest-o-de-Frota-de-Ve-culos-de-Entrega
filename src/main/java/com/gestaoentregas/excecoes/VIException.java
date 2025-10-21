package com.gestaoentregas.excecoes;

public class VIException extends Exception {

    public VIException() {
        super("Veículo indisponível.");
    }

    public VIException(String mensagem) {
        super(mensagem);
    }

    public VIException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
