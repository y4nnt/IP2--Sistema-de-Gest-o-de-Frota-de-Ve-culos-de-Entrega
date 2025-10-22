package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Entrega;

public interface IRepositorioEntrega {
    void cadastrarEntrega(Entrega entrega);
    void atualizarEntrega(Entrega entrega);
    void removerEntrega(String codEntrega);
    Entrega buscarEntrega(String codEntrega);
}
