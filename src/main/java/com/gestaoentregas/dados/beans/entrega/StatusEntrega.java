package com.gestaoentregas.dados.beans.entrega;

public enum StatusEntrega {
    PENDENTE(0),
    EM_TRANSITO(1),
    ENTREGUE(2),
    CANCELADA(3),
    PROBLEMA(4);

    private final int codigo;

    StatusEntrega(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
