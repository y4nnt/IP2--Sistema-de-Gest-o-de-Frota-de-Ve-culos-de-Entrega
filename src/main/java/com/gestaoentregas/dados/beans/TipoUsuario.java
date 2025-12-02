package com.gestaoentregas.dados.beans;

public enum TipoUsuario {
    ADMIN(0),
    MOTORISTA(1),
    CLIENTE(2);

    private final int  codigo;

    TipoUsuario(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {return this.codigo;}

}
