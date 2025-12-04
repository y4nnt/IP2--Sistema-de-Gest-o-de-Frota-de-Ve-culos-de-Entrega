package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Repository
public class RepositorioVeiculo implements IRepositorioVeiculo {
    private ArrayList<Veiculo> veiculos;
    private int contadorId = 201;

    private RepositorioVeiculo() {
        this.veiculos = new ArrayList<>();
    }


    @Override
    public void cadastrarVeiculo(Veiculo veiculo) {
        if (veiculo.getIdVeiculo() == 0){
            veiculo.setIdVeiculo(contadorId);
            contadorId++;
        }
        veiculos.add(veiculo);
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

        if (i != -1) {
            this.veiculos.remove(i);
        }
    }

    @Override
    public Veiculo buscarVeiculo(int id) {
        Veiculo veiculo = null;
        int i = procurarIndice(id);
        if (i != -1) {
            veiculo = this.veiculos.get(i);
        }
        return veiculo;
    }

    private int procurarIndice(int id) {
        int indice = -1;
        for (int i = 0; i < this.veiculos.size(); i++) {
            if (this.veiculos.get(i).getIdVeiculo() == id) {
                indice = i;
            }
        }
        return indice;
    }

}