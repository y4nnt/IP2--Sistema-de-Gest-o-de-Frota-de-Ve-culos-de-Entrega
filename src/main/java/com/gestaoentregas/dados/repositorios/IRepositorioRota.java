package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Rota;

import java.util.ArrayList;

public interface IRepositorioRota {
    void cadastrarRota(Rota rota);
    void atualizarRota(Rota rota);
    void removerRota(int id);
    Rota buscarRota(int id);
    public ArrayList<Rota> listarRotas();
}
