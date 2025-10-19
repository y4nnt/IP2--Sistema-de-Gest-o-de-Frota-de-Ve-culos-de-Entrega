package com.gestaoentregas.repositorios;

import com.gestaoentregas.classes.veiculo.Veiculo;

public class RepositorioVeiculoArray implements IRepositorioVeiculo {
    private Veiculo[] veiculos;
    private int proximo = 0;

    public RepositorioVeiculoArray(int tamanho) {
        this.veiculos = new Veiculo[tamanho];
        this.proximo = 0;
    }

    @Override
    public void cadastrarVeiculo(Veiculo veiculo) {
        if (this.proximo == this.veiculos.length) {
            this.duplicaArrayVeiculo();
        }
        this.veiculos[this.proximo] = veiculo;
        this.proximo++;
        System.out.println("Veiculo cadastrado com sucesso!");
    }
    @Override
    public Veiculo buscarVeiculo(int id) {
        int i = procurarIndice(id);
        Veiculo resultado = null;
        if (i != this.proximo) {
            resultado = this.veiculos[i];
        }
    return resultado;
    }

    @Override
    public void atualizarVeiculo(Veiculo veiculo) {
        int i = procurarIndice(veiculo.getIdVeiculo());
        if (i < this.proximo) {
            this.veiculos[i] = veiculo;
            System.out.println("Veículo atualizado com sucesso!");
        } else {
            System.out.println("Veículo não encontrado!");
        }
    }

    @Override
    public void removerVeiculo(int id) {
        int i = this.procurarIndice(id);
        if (i < this.proximo) {
            this.veiculos[i] = this.veiculos[this.proximo -1];
            this.veiculos[this.proximo -1] = null;
            this.proximo =  this.proximo -1;
            System.out.println("Veiculo removido com sucesso!");
        }
        else  {
            System.out.println("Veiculo nao encontrado!");
        }

    }
    private int procurarIndice(int id) {
        int i = 0;
        boolean achou = false;
        while ((!achou) && (i < this.proximo)) {
            if (id == (this.veiculos[i].getIdVeiculo()))
                achou = true;
            else {
                i++;
            }
        }
        return i;
    }
    private void duplicaArrayVeiculo() {
        int novoTamanho = (this.veiculos.length > 0) ? this.veiculos.length * 2 : 1;
        Veiculo[] arrayDuplicado = new Veiculo[novoTamanho];
        for (int i = 0; i < this.proximo; i++) {
            arrayDuplicado[i] = this.veiculos[i];
        }
        this.veiculos = arrayDuplicado;
    }

}