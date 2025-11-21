package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Repository
public class RepositorioEntrega implements IRepositorioEntrega{
    private ArrayList<Entrega> entregas;

    public RepositorioEntrega() {
        this.entregas = new ArrayList<>();
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
