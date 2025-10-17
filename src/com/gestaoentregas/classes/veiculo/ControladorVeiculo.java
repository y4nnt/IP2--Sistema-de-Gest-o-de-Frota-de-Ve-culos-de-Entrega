package com.gestaoentregas.classes.veiculo;

public class ControladorVeiculo {
    private IRepositorioVeiculo repositorioVeiculo;

    public ControladorVeiculo(IRepositorioVeiculo repositorioVeiculo) {
        this.repositorioVeiculo = repositorioVeiculo;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        Veiculo existente = repositorioVeiculo.buscarVeiculo(veiculo.getIdVeiculo());
        if (existente == null) {
            repositorioVeiculo.cadastrarVeiculo(veiculo);
        } else {
            System.out.println("Já existe um veículo com este ID!");
        }
    }

    public Veiculo buscarVeiculo(int id) {
        Veiculo veiculo = repositorioVeiculo.buscarVeiculo(id);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado!");
        }
        return veiculo;
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        Veiculo existente = repositorioVeiculo.buscarVeiculo(veiculo.getIdVeiculo());
        if (existente != null) {
            repositorioVeiculo.atualizarVeiculo(veiculo);
        } else {
            System.out.println("Veículo não encontrado para atualização!");
        }
    }

    public void removerVeiculo(int id) {
        Veiculo existente = repositorioVeiculo.buscarVeiculo(id);
        if (existente != null) {
            repositorioVeiculo.removerVeiculo(id);
        } else {
            System.out.println("Veículo não encontrado para remoção!");
        }
    }
}
