package com.gestaoentregas.negocio.controladores;

import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.repositorios.IRepositorioVeiculoMotorista;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.dados.beans.VeiculoMotorista;
import com.gestaoentregas.dados.repositorios.IRepositorioMotorista;
import com.gestaoentregas.dados.repositorios.IRepositorioVeiculo;
import com.gestaoentregas.excecoes.CException;
import com.gestaoentregas.excecoes.MIException;
import com.gestaoentregas.excecoes.VIException;

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

    public void desassociar(int idVeiculo) {
        repositorioAssociacoes.removerPorVeiculo(idVeiculo);
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
