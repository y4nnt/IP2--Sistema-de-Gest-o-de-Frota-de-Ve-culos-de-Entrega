package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.repositorios.IRepositorioVeiculo;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.dados.repositorios.RepositorioVeiculo;
import com.gestaoentregas.excecoes.PlException;
import com.gestaoentregas.excecoes.VCException;
import com.gestaoentregas.excecoes.VIException;
import org.springframework.stereotype.Service;

@Service
public class ServicoVeiculo {
    private final RepositorioVeiculo repositorioVeiculo;

    public ServicoVeiculo(RepositorioVeiculo repositorioVeiculo) {
        this.repositorioVeiculo = repositorioVeiculo;
    }

    public void cadastrarVeiculo(Veiculo novoVeiculo) throws Exception {

        // 1. Busca para ver se já existe (supondo que busque por ID ou Placa)
        // Nota: Se for um cadastro novo, geralmente não se busca por ID, mas sim por PLACA.
        // Verifique se você não deveria estar usando buscarPorPlaca(String placa).

        Veiculo veiculoExistente = repositorioVeiculo.buscarVeiculo(novoVeiculo.getIdVeiculo());

        // 2. A CORREÇÃO: Verifique se não é nulo antes!
        if (veiculoExistente != null) {
            throw new Exception("Erro: Já existe um veículo cadastrado com esse ID.");
        }

        repositorioVeiculo.cadastrarVeiculo(novoVeiculo);
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
