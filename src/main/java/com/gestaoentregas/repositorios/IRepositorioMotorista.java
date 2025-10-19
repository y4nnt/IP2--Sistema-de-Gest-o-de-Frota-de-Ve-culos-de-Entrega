package com.gestaoentregas.repositorios;

import com.gestaoentregas.classes.motorista.Motorista;

public interface IRepositorioMotorista {
    void cadastrarMotorista(Motorista motorista);
    void atualizarMotorista(Motorista motorista);
    void removerMotorista(int id);
    Motorista buscarMotorista(int id);
}
