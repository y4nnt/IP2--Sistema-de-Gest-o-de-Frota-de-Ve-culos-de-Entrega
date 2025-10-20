package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.veiculo.Veiculo;

import java.util.ArrayList;

public class RepositorioVeiculo implements IRepositorioVeiculo {
    private ArrayList<Veiculo> veiculos;

    private static ArrayList<RepositorioVeiculo> repositorioVeiculo;

    private RepositorioVeiculo() {
        this.veiculos = new ArrayList<>();
    }

    public static ArrayList<RepositorioVeiculo> getInstance() {
        // Se a instância ainda não foi criada...
        if (repositorioVeiculo == null) {
            // ...cria a única instância
            repositorioVeiculo = new ArrayList<RepositorioVeiculo>();
        }
        // Retorna a instância que já existe ou acabou de ser criada
        return repositorioVeiculo;
    }

    @Override
    public void cadastrarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }
    @Override
    public Veiculo buscarVeiculo(int id) {
        Veiculo veiculo = null;
        int i = procurarIndice(id);
        if (i != 0) {
            veiculo = this.veiculos.get(i);
        }
        return veiculo; // não encontrado
    }

    @Override
    public void atualizarVeiculo(Veiculo veiculo) {
        int i = procurarIndice(veiculo.getIdVeiculo());
        if (i != -1) {
            this.veiculos.set(i, veiculo);
        }
    }

    @Override
    public void removerVeiculo(int id) {
        int i = procurarIndice(id);
        // substitui o elemento removido pelo último
        if (i != 0) {
            this.veiculos.remove(i);
        }
    }
    private int procurarIndice(int id) {
        int indice = -1;
        for (int i = 0; i < this.veiculos.size(); i++) {
            if (this.veiculos.get(i).getIdVeiculo() == id) {
                indice = i;
            }
        }
        return indice; // não encontrado
    }

}