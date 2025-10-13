public enum StatusVeiculo {
    DISPONIVEL(0),
    INDISPONIVEL(1);

    private final int codigo;

    StatusVeiculo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public static StatusVeiculo fromCodigo(int codigo) {
        for (StatusVeiculo status : values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de disponibilidade inválido: " + codigo);
    }
}
