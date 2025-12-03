package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.veiculo.Manutencao;

import java.util.List;

public interface IRepositorioManutencao {
    void salvar(String placa, Manutencao manutencao);
    List<Manutencao> buscarPorPlaca(String placa);
    void cadastrarManutencao(int idVeiculo, Manutencao novaManutencao);
}
