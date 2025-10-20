package com.gestaoentregas.excecoes;

public class MIException extends Exception {

    public MIException() {
        super("Motorista indisponível.");
    }
}
