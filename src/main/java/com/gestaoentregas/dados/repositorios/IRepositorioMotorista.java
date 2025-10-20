package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.motorista.Motorista;

public interface IRepositorioMotorista {
    void cadastrarMotorista(Motorista motorista);
    void atualizarMotorista(Motorista motorista);
    void removerMotorista(int id);
    Motorista buscarMotorista(int id);
}
