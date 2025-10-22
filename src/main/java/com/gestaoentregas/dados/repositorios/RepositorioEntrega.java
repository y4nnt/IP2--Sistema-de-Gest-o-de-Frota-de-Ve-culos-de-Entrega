package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Entrega;


import java.util.ArrayList;

public class RepositorioEntrega implements IRepositorioEntrega{
    private ArrayList<Entrega> entregas;

    private static RepositorioEntrega repositorioEntregas;

    private RepositorioEntrega() {
        this.entregas = new ArrayList<>();
    }

    public static RepositorioEntrega getInstance() {
        // Se a instância ainda não foi criada...
        if (repositorioEntregas == null) {
            // ...cria a única instância
            repositorioEntregas = new RepositorioEntrega();
        }
        // Retorna a instância que já existe ou acabou de ser criada
        return repositorioEntregas;
    }
    @Override
    public void cadastrarEntrega(Entrega entrega) {
        entregas.add(entrega);
    }

    @Override
    public void atualizarEntrega(Entrega entrega) {
        int i = procurarIndice(entrega.getCodEntrega());
        if (i != -1) {
            this.entregas.set(i, entrega);
        }
    }


    @Override
    public void removerEntrega(String codEntrega) {
        int i = procurarIndice(codEntrega);

        if (i != -1) {
            this.entregas.remove(i);
        }
    }

    @Override
    public Entrega buscarEntrega(String codEntrega) {
        Entrega entrega = null;
        int i = procurarIndice(codEntrega);
        if (i != -1) {
            entrega = this.entregas.get(i);
        }
        return entrega;
    }

    private int procurarIndice(String codigo) {
        int indice = -1;
        for (int i = 0; i < this.entregas.size(); i++) {
            if (this.entregas.get(i).getCodEntrega().equals(codigo)) {
                indice = i;
            }
        }
        return indice;
    }
}
