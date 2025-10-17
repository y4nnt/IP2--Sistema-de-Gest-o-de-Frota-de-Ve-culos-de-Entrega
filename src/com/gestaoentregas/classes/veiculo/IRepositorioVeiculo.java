package com.gestaoentregas.classes.veiculo;

public interface IRepositorioVeiculo {
    void cadastrarVeiculo(Veiculo veiculo);
    void atualizarVeiculo(Veiculo veiculo);
    void removerVeiculo(int id);
    Veiculo buscarVeiculo(int id);

}
