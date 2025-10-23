package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.repositorios.*;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.dados.beans.VeiculoMotorista;
import com.gestaoentregas.excecoes.CException;
import com.gestaoentregas.excecoes.CIException;
import com.gestaoentregas.excecoes.MIException;
import com.gestaoentregas.excecoes.VIException;

public class ServicoVeiculoMotorista {
    private final IRepositorioVeiculoMotorista repositorioAssociacoes;
    private final IRepositorioMotorista repositorioMotorista;
    private final IRepositorioVeiculo repositorioVeiculo;

    public ServicoVeiculoMotorista() {
        this.repositorioAssociacoes = RepositorioVeiculoMotorista.getInstance();
        this.repositorioMotorista = RepositorioMotorista.getInstance();
        this.repositorioVeiculo = RepositorioVeiculo.getInstance();
    }

    public void associar(int idMotorista, int idVeiculo) throws MIException, VIException, CException {
        Motorista motorista = repositorioMotorista.buscarMotorista(idMotorista);
        Veiculo veiculo = repositorioVeiculo.buscarVeiculo(idVeiculo);

        if (motorista == null) {
            throw new MIException();
        } else if (veiculo == null) {
            throw new VIException();
        } else if (repositorioAssociacoes.buscarPorVeiculo(idVeiculo) != null) {
            throw new CException();
        }

        VeiculoMotorista nova = new VeiculoMotorista(motorista, veiculo);
        repositorioAssociacoes.cadastrar(nova);
    }

    public void desassociar(int idVeiculo) throws CIException {
        if (repositorioVeiculo.buscarVeiculo(idVeiculo) != null) {
            repositorioAssociacoes.removerPorVeiculo(idVeiculo);
        } else{
            throw new CIException();
        }

    }

    /* Mesma coisa (vê no repositórioVeiculoMotorista)
    public void listarAssociacoes() {
        VeiculoMotorista[] lista = repositorioAssociacoes.listar();
        if (lista.length == 0) {
            System.out.println("Nenhuma associação encontrada.");
        } else {
            for (VeiculoMotorista vm : lista) {
                System.out.println(vm);
            }
        }
    } */
}
