package com.gestaoentregas.classes.veiculomotorista;

public interface IRepositorioVeiculoMotorista {
    void cadastrar(VeiculoMotorista associacao);
    void removerPorVeiculo(int idVeiculo);
    VeiculoMotorista buscarPorVeiculo(int idVeiculo);
    VeiculoMotorista[] listar();
}
