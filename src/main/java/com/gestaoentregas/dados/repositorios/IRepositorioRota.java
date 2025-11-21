package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Rota;

public interface IRepositorioRota {
    void cadastrarRota(Rota rota);
    void atualizarRota(Rota rota);
    void removerRota(String id);
    Rota buscarRota(String id);
}
