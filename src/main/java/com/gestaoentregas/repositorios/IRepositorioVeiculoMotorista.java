package com.gestaoentregas.repositorios;

import com.gestaoentregas.classes.veiculomotorista.VeiculoMotorista;

public interface IRepositorioVeiculoMotorista {
    void cadastrar(VeiculoMotorista associacao);
    void removerPorVeiculo(int idVeiculo);
    VeiculoMotorista buscarPorVeiculo(int idVeiculo);
    VeiculoMotorista[] listar();
}
