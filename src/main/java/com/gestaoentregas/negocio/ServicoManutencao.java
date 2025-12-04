package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.veiculo.Manutencao;
import com.gestaoentregas.dados.repositorios.IRepositorioManutencao;
import com.gestaoentregas.dados.repositorios.RepositorioManutencao;
import com.gestaoentregas.excecoes.VIException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ServicoManutencao {

    private final RepositorioManutencao repositorioManutencao;
    private final ServicoVeiculo servicoVeiculo;

    public ServicoManutencao(RepositorioManutencao repositorioManutencao, ServicoVeiculo servicoVeiculo) {
        this.repositorioManutencao = repositorioManutencao;
        this.servicoVeiculo = servicoVeiculo;
    }


    public void registrarManutencao(int idVeiculo, String motivo, LocalDate inicio, LocalDate fim) throws VIException, IllegalArgumentException {
        servicoVeiculo.buscarVeiculo(idVeiculo);

        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("As datas de início e fim não podem ser nulas.");
        }
        if (fim.isBefore(inicio)) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }


        Manutencao novaManutencao = new Manutencao(servicoVeiculo.buscarVeiculo(idVeiculo), motivo);
        novaManutencao.addManutencao(inicio, fim);

        repositorioManutencao.cadastrarManutencao(idVeiculo, novaManutencao);
    }
}