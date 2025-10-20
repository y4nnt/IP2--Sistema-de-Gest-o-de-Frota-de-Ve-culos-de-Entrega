package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.veiculo.Veiculo;

public interface IRepositorioVeiculo {
    void cadastrarVeiculo(Veiculo veiculo);
    void atualizarVeiculo(Veiculo veiculo);
    void removerVeiculo(int id);
    Veiculo buscarVeiculo(int id);

}
