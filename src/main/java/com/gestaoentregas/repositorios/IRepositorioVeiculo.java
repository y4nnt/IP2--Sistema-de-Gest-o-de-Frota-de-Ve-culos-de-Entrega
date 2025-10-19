package com.gestaoentregas.repositorios;

import com.gestaoentregas.classes.veiculo.Veiculo;

public interface IRepositorioVeiculo {
    void cadastrarVeiculo(Veiculo veiculo);
    void atualizarVeiculo(Veiculo veiculo);
    void removerVeiculo(int id);
    Veiculo buscarVeiculo(int id);

}
