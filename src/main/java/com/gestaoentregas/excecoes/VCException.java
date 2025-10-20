package com.gestaoentregas.excecoes;

public class VCException extends Exception {

    public VCException() {
        super("Veículo já cadastrado.");
    }
}
