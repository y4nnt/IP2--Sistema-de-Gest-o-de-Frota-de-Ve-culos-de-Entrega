package com.gestaoentregas.classes.motorista;

public interface IRepositorioMotorista {
    void cadastrarMotorista(Motorista motorista);
    void atualizarMotorista(Motorista motorista);
    void removerMotorista(int id);
    Motorista buscarMotorista(int id);
}
