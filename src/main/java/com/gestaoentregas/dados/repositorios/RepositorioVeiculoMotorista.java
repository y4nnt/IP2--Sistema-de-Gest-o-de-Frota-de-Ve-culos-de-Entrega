package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.VeiculoMotorista;
import com.gestaoentregas.excecoes.CIException;

import java.util.ArrayList;

public class RepositorioVeiculoMotorista implements IRepositorioVeiculoMotorista {
    private ArrayList<VeiculoMotorista> veiculosmotoristas;

    private static RepositorioVeiculoMotorista repositorioVeiculoMotorista;

    private RepositorioVeiculoMotorista() {
        this.veiculosmotoristas = new ArrayList<>();
    }

    public static RepositorioVeiculoMotorista getInstance() {
        // Se a instância ainda não foi criada...
        if (repositorioVeiculoMotorista == null) {
            // ...cria a única instância
            repositorioVeiculoMotorista = new RepositorioVeiculoMotorista();
        }
        // Retorna a instância que já existe ou acabou de ser criada
        return repositorioVeiculoMotorista;
    }

    @Override
    public void cadastrar(VeiculoMotorista associacao) {
        veiculosmotoristas.add(associacao);
    }

    @Override
    public void removerPorVeiculo(int idVeiculo) {
        for (int i = 0; i < this.veiculosmotoristas.size(); i++) {
            if (veiculosmotoristas.get(i).getVeiculoEntrega().getIdVeiculo() == idVeiculo) {
                veiculosmotoristas.remove(i);
                i = veiculosmotoristas.size();
            }
        }
    }

    @Override
    public VeiculoMotorista buscarPorVeiculo(int idVeiculo) {
        VeiculoMotorista veiculomotorista = null;
        for (int i = 0; i < this.veiculosmotoristas.size(); i++) {
            if (veiculosmotoristas.get(i).getVeiculoEntrega().getIdVeiculo() == idVeiculo) {
                veiculomotorista = this.veiculosmotoristas.get(i);
            }
        }
        return veiculomotorista;
    }

    @Override
    public void removerPorMotorista(int idMotorista) {
        for (int i = 0; i < this.veiculosmotoristas.size(); i++) {
            if (veiculosmotoristas.get(i).getMotoristaEntrega().getIdMotorista() == idMotorista) {
                veiculosmotoristas.remove(i);
                i = veiculosmotoristas.size();
            }
        }
    }

    @Override
    public VeiculoMotorista buscarPorMotorista(int idMotorista) {
        VeiculoMotorista veiculomotorista = null;
        for (int i = 0; i < this.veiculosmotoristas.size(); i++) {
            if (veiculosmotoristas.get(i).getMotoristaEntrega().getIdMotorista() == idMotorista) {
                veiculomotorista = this.veiculosmotoristas.get(i);
            }
        }
        return veiculomotorista;
    }

    /* Aqui seria só um toString não?
    public VeiculoMotorista[] listar() {
        VeiculoMotorista[] copia = new VeiculoMotorista[indice];
        System.arraycopy(lista, 0, copia, 0, indice);
        return copia;
    }*/

}
