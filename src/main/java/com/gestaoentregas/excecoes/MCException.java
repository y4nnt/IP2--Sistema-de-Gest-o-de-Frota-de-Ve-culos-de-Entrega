package com.gestaoentregas.excecoes;

public class MCException extends Exception {

    public MCException() {
        super("Motorista já cadastrado.");
    }
}
