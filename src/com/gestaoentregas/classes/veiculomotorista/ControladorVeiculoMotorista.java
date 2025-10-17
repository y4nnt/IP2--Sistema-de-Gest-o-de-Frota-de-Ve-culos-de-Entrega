package com.gestaoentregas.classes.veiculomotorista;

import com.gestaoentregas.classes.motorista.*;
import com.gestaoentregas.classes.veiculo.*;

public class ControladorVeiculoMotorista {
    private IRepositorioVeiculoMotorista repositorioAssociacoes;
    private IRepositorioMotorista repositorioMotorista;
    private IRepositorioVeiculo repositorioVeiculo;

    public ControladorVeiculoMotorista(IRepositorioVeiculoMotorista repoAssoc,
                                       IRepositorioMotorista repoMotorista,
                                       IRepositorioVeiculo repoVeiculo) {
        this.repositorioAssociacoes = repoAssoc;
        this.repositorioMotorista = repoMotorista;
        this.repositorioVeiculo = repoVeiculo;
    }

    public void associar(int idMotorista, int idVeiculo) {
        Motorista motorista = repositorioMotorista.buscarMotorista(idMotorista);
        Veiculo veiculo = repositorioVeiculo.buscarVeiculo(idVeiculo);

        if (motorista == null || veiculo == null) {
            System.out.println("Motorista ou veículo não encontrado!");
            return;
        }

        if (repositorioAssociacoes.buscarPorVeiculo(idVeiculo) != null) {
            System.out.println("Este veículo já está associado a outro motorista!");
            return;
        }

        VeiculoMotorista nova = new VeiculoMotorista(motorista, veiculo);
        repositorioAssociacoes.cadastrar(nova);
        System.out.println("Associação criada com sucesso!");
    }

    public void desassociar(int idVeiculo) {
        repositorioAssociacoes.removerPorVeiculo(idVeiculo);
    }

    public void listarAssociacoes() {
        VeiculoMotorista[] lista = repositorioAssociacoes.listar();
        if (lista.length == 0) {
            System.out.println("Nenhuma associação encontrada.");
        } else {
            for (VeiculoMotorista vm : lista) {
                System.out.println(vm);
            }
        }
    }
}
