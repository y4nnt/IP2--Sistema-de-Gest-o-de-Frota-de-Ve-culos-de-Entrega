package com.gestaoentregas.repositorios;

import com.gestaoentregas.classes.veiculomotorista.VeiculoMotorista;

public class RepositorioVeiculoMotoristaArray implements IRepositorioVeiculoMotorista {
    private VeiculoMotorista[] lista;
    private int indice;

    public RepositorioVeiculoMotoristaArray(int tamanho) {
        lista = new VeiculoMotorista[tamanho];
        indice = 0;
    }

    @Override
    public void cadastrar(VeiculoMotorista associacao) {
        if (indice < lista.length) {
            lista[indice++] = associacao;
        } else {
            System.out.println("Repositório cheio!");
        }
    }

    @Override
    public void removerPorVeiculo(int idVeiculo) {
        for (int i = 0; i < indice; i++) {
            if (lista[i].getVeiculoEntrega().getIdVeiculo() == idVeiculo) {
                lista[i] = lista[indice - 1];
                lista[indice - 1] = null;
                indice--;
                System.out.println("Associação removida!");
                return;
            }
        }
        System.out.println("Associação não encontrada.");
    }

    @Override
    public VeiculoMotorista buscarPorVeiculo(int idVeiculo) {
        for (int i = 0; i < indice; i++) {
            if (lista[i].getVeiculoEntrega().getIdVeiculo() == idVeiculo) {
                return lista[i];
            }
        }
        return null;
    }

    @Override
    public VeiculoMotorista[] listar() {
        VeiculoMotorista[] copia = new VeiculoMotorista[indice];
        System.arraycopy(lista, 0, copia, 0, indice);
        return copia;
    }
}
