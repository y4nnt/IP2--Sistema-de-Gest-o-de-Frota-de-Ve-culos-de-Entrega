package com.gestaoentregas.excecoes;

public class UCException extends RuntimeException {
    public UCException() {super("Usuário já cadastrado");}

    public UCException(String message) {
    super(message);
  }

    public UCException(String message, Throwable cause) {}
}
