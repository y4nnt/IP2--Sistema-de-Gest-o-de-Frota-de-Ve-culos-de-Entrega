package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Entrega;

import java.util.ArrayList;

public interface IRepositorioEntrega {
    void cadastrarEntrega(Entrega entrega);
    void atualizarEntrega(Entrega entrega);
    void removerEntrega(String codEntrega);
    Entrega buscarEntrega(String codEntrega);
    ArrayList<Entrega> listarEntregas();
}
