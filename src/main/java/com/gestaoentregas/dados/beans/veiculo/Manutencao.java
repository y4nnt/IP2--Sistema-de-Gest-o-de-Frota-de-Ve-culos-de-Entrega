package com.gestaoentregas.dados.beans.veiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manutencao {
    private Veiculo veiculo;
    private String motivoManutencao;
    private List<PeriodoIndisponivelVeiculo> dataManutencao;

    public Manutencao(Veiculo veiculo, String motivoManutencao) {
        this.veiculo = veiculo;
        this.motivoManutencao = motivoManutencao;
        dataManutencao =  new ArrayList<PeriodoIndisponivelVeiculo>();
    }

    public String getMotivoManutencao() {
        return motivoManutencao;
    }

    public void setMotivoManutencao(String motivoManutencao) {
        this.motivoManutencao = motivoManutencao;
    }

    public List<PeriodoIndisponivelVeiculo> getDataManutencao() {
        return dataManutencao;
    }

    public void addManutencao(LocalDate inicio, LocalDate fim){
        PeriodoIndisponivelVeiculo manutencao = new PeriodoIndisponivelVeiculo(inicio, fim, "Manutenção");
        this.dataManutencao.add(manutencao);
    }
}
