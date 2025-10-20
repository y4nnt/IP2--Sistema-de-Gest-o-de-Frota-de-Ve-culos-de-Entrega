package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.VeiculoMotorista;

public interface IRepositorioVeiculoMotorista {
    void cadastrar(VeiculoMotorista associacao);
    void removerPorVeiculo(int idVeiculo);
    VeiculoMotorista buscarPorVeiculo(int idVeiculo);
    void removerPorMotorista(int idMotorista);
    VeiculoMotorista buscarPorMotorista(int idMotorista);
}
