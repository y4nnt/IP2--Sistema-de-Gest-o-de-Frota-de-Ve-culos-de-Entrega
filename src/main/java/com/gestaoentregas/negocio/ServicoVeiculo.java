package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.repositorios.IRepositorioVeiculo;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.dados.repositorios.RepositorioVeiculo;
import com.gestaoentregas.excecoes.PlException;
import com.gestaoentregas.excecoes.VCException;
import com.gestaoentregas.excecoes.VIException;

public class ServicoVeiculo {
    private final IRepositorioVeiculo repositorioVeiculo;

    public ServicoVeiculo() {
        this.repositorioVeiculo = RepositorioVeiculo.getInstance();
    }

    public void cadastrarVeiculo(Veiculo veiculo) throws VCException, PlException {
        if (repositorioVeiculo.buscarVeiculo(veiculo.getIdVeiculo()) != null) {
            throw new VCException();
        } else if (repositorioVeiculo.buscarVeiculo(veiculo.getIdVeiculo()).getPlacaVeiculo() == null) {
            throw new PlException();
        }
        repositorioVeiculo.cadastrarVeiculo(veiculo);
    }

    public void atualizarVeiculo(Veiculo veiculo) throws VIException {
        if (repositorioVeiculo.buscarVeiculo(veiculo.getIdVeiculo()) == null) {
            throw new VIException();
        }
        repositorioVeiculo.atualizarVeiculo(veiculo);
    }

    public void removerVeiculo(int id) throws VIException {
        if (repositorioVeiculo.buscarVeiculo(id) == null) {
            throw new VIException();
        }
        repositorioVeiculo.removerVeiculo(id);
    }

    public Veiculo buscarVeiculo(int id) throws VIException {
        Veiculo veiculo = repositorioVeiculo.buscarVeiculo(id);
        if (veiculo == null) {
            throw new VIException();
        }
        return veiculo;
    }
}
