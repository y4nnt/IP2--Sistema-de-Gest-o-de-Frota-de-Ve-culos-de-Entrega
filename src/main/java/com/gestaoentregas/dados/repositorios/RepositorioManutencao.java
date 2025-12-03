package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.veiculo.Manutencao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioManutencao implements IRepositorioManutencao {
    private final Map<String, List<Manutencao>> registros = new HashMap<>();
    private static RepositorioManutencao instance;

    private RepositorioManutencao() {}

    @Override
    public void salvar(String placa, Manutencao manutencao) {
        // Se a placa não existe, cria uma nova lista para ela
        registros.computeIfAbsent(placa, k -> new ArrayList<>()).add(manutencao);
        System.out.println("Repositório: Novo motivo de manutenção registrado para a placa " + placa);
    }

    @Override
    public List<Manutencao> buscarPorPlaca(String placa) {
        return registros.getOrDefault(placa, new ArrayList<>());
    }

    @Override
    public void cadastrarManutencao(int idVeiculo, Manutencao novaManutencao) {

    }
}