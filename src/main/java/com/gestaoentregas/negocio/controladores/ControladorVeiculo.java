package com.gestaoentregas.negocio.controladores;

import com.gestaoentregas.dados.repositorios.IRepositorioVeiculo;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.excecoes.VCException;
import com.gestaoentregas.excecoes.VIException;

public class ControladorVeiculo {
    private IRepositorioVeiculo repositorioVeiculo;

    public ControladorVeiculo(IRepositorioVeiculo repositorioVeiculo) {
        this.repositorioVeiculo = repositorioVeiculo;
    }

    public void cadastrarVeiculo(Veiculo veiculo) throws VCException {
        if (repositorioVeiculo.buscarVeiculo(veiculo.getIdVeiculo()) == null) {
            repositorioVeiculo.cadastrarVeiculo(veiculo);
        } else {
            throw new VCException();
        }
    }

    public Veiculo buscarVeiculo(int id) throws VIException {
        Veiculo veiculo = repositorioVeiculo.buscarVeiculo(id);
        if (veiculo == null) {
            throw new VIException();
        }
        return veiculo;
    }

    public void atualizarVeiculo(Veiculo veiculo) throws VIException {
        if (repositorioVeiculo.buscarVeiculo(veiculo.getIdVeiculo()) != null) {
            repositorioVeiculo.atualizarVeiculo(veiculo);
        } else {
            throw new VIException();
        }
    }

    public void removerVeiculo(int id) throws VIException {
        if (repositorioVeiculo.buscarVeiculo(id) != null) {
            repositorioVeiculo.removerVeiculo(id);
        } else {
            throw new VIException();
        }
    }
}
