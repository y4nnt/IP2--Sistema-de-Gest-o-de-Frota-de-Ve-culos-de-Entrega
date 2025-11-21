package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Rota;

import java.util.ArrayList;

public class RepositorioRota implements IRepositorioRota {
    private ArrayList<Rota> rotas;

    private static RepositorioRota repositorioRotas;

    private RepositorioRota() {
        this.rotas = new ArrayList<>();
    }

    public static RepositorioRota getInstance() {
        // Se a instância ainda não foi criada...
        if (repositorioRotas == null) {
            // ...cria a única instância
            repositorioRotas = new RepositorioRota();
        }
        // Retorna a instância que  já existe ou acabou de ser criada
        return repositorioRotas;
    }

    @Override
    public void cadastrarRota(Rota rota) {
        rotas.add(rota);
    }

    @Override
    public void atualizarRota(Rota rota) {
        int i = procurarIndice(rota.getIdRota());
        if (i != -1) {
            this.rotas.set(i, rota);
        }
    }

    @Override
    public void removerRota(String id) {
        int i = procurarIndice(id);
        if (i != -1) {
            this.rotas.remove(i);
        }
    }

    @Override
    public Rota buscarRota(String id) {
        Rota rota = null;
        int i = procurarIndice(id);
        if (i != -1) {
            rota = this.rotas.get(i);
        }
        return rota;
    }

    private int procurarIndice(String id) {
        int indice = -1;
        for (int i = 0; i < this.rotas.size(); i++) {
            if (this.rotas.get(i).getIdRota().equals(id)) {
                indice = i;
            }
        }
        return indice;
    }
}
