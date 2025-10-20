package com.gestaoentregas.dados.beans.motorista;

public enum DisponibilidadeMotorista {
    DISPONIVEL(0),
    INDISPONIVEL(1);

    private final int codigo;

    DisponibilidadeMotorista(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public static DisponibilidadeMotorista fromCodigo(int codigo) {
        for (DisponibilidadeMotorista status : values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de disponibilidade inválido: " + codigo);
    }
}
