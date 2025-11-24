package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.motorista.Motorista;

import java.util.ArrayList;

public interface IRepositorioMotorista {
    void cadastrarMotorista(Motorista motorista);
    void atualizarMotorista(Motorista motorista);
    void removerMotorista(int id);
    Motorista buscarMotorista(int id);
    ArrayList<Motorista> listarMotoristas();
}
